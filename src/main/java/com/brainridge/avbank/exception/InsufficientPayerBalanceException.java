package com.brainridge.avbank.exception;

import java.math.BigDecimal;

public class InsufficientPayerBalanceException extends RuntimeException{
    public InsufficientPayerBalanceException(String payerEmail, BigDecimal balance, BigDecimal amount) {
        super("Payers's balance (" + balance + ") is less than the transaction amount (" + amount + ") for email: " + payerEmail);
    }
}
