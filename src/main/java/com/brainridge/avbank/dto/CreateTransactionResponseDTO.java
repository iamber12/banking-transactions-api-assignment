package com.brainridge.avbank.dto;

import com.brainridge.avbank.entity.TransactionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CreateTransactionResponseDTO {
    private UUID transactionId;
    private String payerEmail;
    private String payeeEmail;
    private BigDecimal amount;
    private TransactionStatus transactionStatus;
}
