package com.brainridge.avbank.dto;

import com.brainridge.avbank.entity.TransactionStatus;
import com.brainridge.avbank.entity.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class TransactionListResponseDTO {
    private UUID transactionId;
    private TransactionStatus transactionStatus;
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal accountBalance;
    private LocalDateTime createdAt;
}
