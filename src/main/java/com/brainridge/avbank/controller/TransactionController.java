package com.brainridge.avbank.controller;

import com.brainridge.avbank.constants.ApiEndpointsConstants;
import com.brainridge.avbank.dto.CreateTransactionResponseDTO;
import com.brainridge.avbank.dto.CreateTransactionRequestDTO;
import com.brainridge.avbank.dto.TransactionListResponseDTO;
import com.brainridge.avbank.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpointsConstants.TRANSACTION_BASE_URL)
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<CreateTransactionResponseDTO> createTransactionAndTransferFunds(@Valid @RequestBody CreateTransactionRequestDTO transactionRequest) {
        CreateTransactionResponseDTO transactionResponse = transactionService.createTransactionAndTransferFunds(transactionRequest);
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionListResponseDTO>> getTransactions(@PathVariable("accountId") Long accountId) {
        List<TransactionListResponseDTO> transactionHistoryResponse = transactionService.getTransactionsByAccountId(accountId);
        return new ResponseEntity<>(transactionHistoryResponse, HttpStatus.OK);
    }

}
