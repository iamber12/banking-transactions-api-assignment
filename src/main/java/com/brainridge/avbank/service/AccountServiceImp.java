package com.brainridge.avbank.service;

import com.brainridge.avbank.dto.CreateAccountRequestDTO;
import com.brainridge.avbank.dto.CreateAccountResponseDTO;
import com.brainridge.avbank.entity.Account;
import com.brainridge.avbank.exception.AccountExistsException;
import com.brainridge.avbank.exception.AccountNotFoundException;
import com.brainridge.avbank.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService{
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO) {
        if(accountRepository.findByEmail(createAccountRequestDTO.getEmail()).isPresent()) {
            throw new AccountExistsException(createAccountRequestDTO.getEmail());
        }
        Account account = new Account(createAccountRequestDTO.getOwnerName(), createAccountRequestDTO.getEmail(), createAccountRequestDTO.getBalance());
        account = accountRepository.save(account);
        return CreateAccountResponseDTO.builder()
                .id(account.getId())
                .ownerName(account.getOwnerName())
                .email(account.getEmail())
                .balance(account.getBalance())
                .build();
    }

    @Override
    public CreateAccountResponseDTO findById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        return CreateAccountResponseDTO.builder()
                .id(account.getId())
                .ownerName(account.getOwnerName())
                .email(account.getEmail())
                .balance(account.getBalance())
                .build();
    }
}
