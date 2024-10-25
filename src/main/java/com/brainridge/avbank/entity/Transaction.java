package com.brainridge.avbank.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="transactions")
public class Transaction {
    @Id
    @SequenceGenerator(name="account_id_sequence", sequenceName = "transaction_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_sequence")
    private Long id;

    @Column(name="accountId", nullable = false)
    private Long accountId;

    @Column(name = "transactionId", nullable = false)
    private UUID transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable=false)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status;

    @Column(name="amount", nullable = false)
    private BigDecimal amount;

    @Column(name="accountBalance", nullable = false)
    private BigDecimal accountBalance;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    public Transaction(Long accountId, UUID transactionId, TransactionType type, TransactionStatus status, BigDecimal amount, BigDecimal accountBalance) {
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.accountBalance = accountBalance;
        this.createdAt = LocalDateTime.now();
    }
}