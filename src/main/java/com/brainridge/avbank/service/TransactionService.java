package com.brainridge.avbank.service;

import com.brainridge.avbank.dto.CreateTransactionResponseDTO;
import com.brainridge.avbank.dto.CreateTransactionRequestDTO;
import com.brainridge.avbank.dto.TransactionListResponseDTO;

import java.util.List;

public interface TransactionService {
    public CreateTransactionResponseDTO createTransactionAndTransferFunds(CreateTransactionRequestDTO createTransactionRequestDTO);
    public List<TransactionListResponseDTO> getTransactionsByAccountId(Long accountId);
}
