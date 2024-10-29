package com.brainridge.avbank.service;

import com.brainridge.avbank.dto.CreateTransactionResponseDTO;
import com.brainridge.avbank.dto.CreateTransactionRequestDTO;
import com.brainridge.avbank.dto.TransactionListResponseDTO;
import com.brainridge.avbank.entity.Account;
import com.brainridge.avbank.entity.Transaction;
import com.brainridge.avbank.entity.TransactionStatus;
import com.brainridge.avbank.entity.TransactionType;
import com.brainridge.avbank.exception.AccountNotFoundException;
import com.brainridge.avbank.exception.InsufficientPayerBalanceException;
import com.brainridge.avbank.exception.SelfTransactionNotAllowedException;
import com.brainridge.avbank.repository.AccountRepository;
import com.brainridge.avbank.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public CreateTransactionResponseDTO createTransactionAndTransferFunds(CreateTransactionRequestDTO createTransactionRequestDTO) {
        String payerEmail = createTransactionRequestDTO.getPayerEmail();
        String payeeEmail = createTransactionRequestDTO.getPayeeEmail();
        BigDecimal transactionAmount = createTransactionRequestDTO.getAmount();

        if(payerEmail.equalsIgnoreCase(payeeEmail)) {
            throw new SelfTransactionNotAllowedException(payeeEmail);
        }

        Account payerAccount = accountRepository.findByEmail(payerEmail)
                                .orElseThrow(() -> new AccountNotFoundException(payerEmail));

        if(payerAccount.getBalance().compareTo(transactionAmount)<0) {
            throw new InsufficientPayerBalanceException(payerEmail, payerAccount.getBalance(), transactionAmount);
        }

        Account payeeAccount = accountRepository.findByEmail(payeeEmail)
                .orElseThrow(() -> new AccountNotFoundException(payeeEmail));

        payerAccount.setBalance(payerAccount.getBalance().subtract(transactionAmount));
        payeeAccount.setBalance(payeeAccount.getBalance().add(transactionAmount));

        accountRepository.saveAll(Arrays.asList(payerAccount, payeeAccount));

        UUID transactionId = UUID.randomUUID();
        Transaction debitTransaction = new Transaction(payerAccount.getId(), transactionId, TransactionType.DEBIT,
                TransactionStatus.SUCCESS, transactionAmount, payerAccount.getBalance());
        Transaction creditTransaction = new Transaction(payeeAccount.getId(), transactionId, TransactionType.CREDIT,
                TransactionStatus.SUCCESS, transactionAmount, payeeAccount.getBalance());

        transactionRepository.saveAll(Arrays.asList(debitTransaction, creditTransaction));

        return CreateTransactionResponseDTO.builder()
                .transactionId(transactionId)
                .transactionStatus(TransactionStatus.SUCCESS)
                .amount(transactionAmount)
                .payeeEmail(payeeEmail)
                .payerEmail(payerEmail)
                .build();
    }

    @Override
    public List<TransactionListResponseDTO> getTransactionsByAccountId(Long accountId) {
        accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        List<Transaction> transactionHistory = transactionRepository.findByAccountId(accountId);

        return transactionHistory.stream()
                .map(this::mapToTransactionListResponseDTO)
                .collect(Collectors.toList());
    }

    private TransactionListResponseDTO mapToTransactionListResponseDTO(Transaction transaction) {
        return TransactionListResponseDTO.builder()
                .transactionId(transaction.getTransactionId())
                .transactionStatus(transaction.getStatus())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .accountBalance(transaction.getAccountBalance())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
