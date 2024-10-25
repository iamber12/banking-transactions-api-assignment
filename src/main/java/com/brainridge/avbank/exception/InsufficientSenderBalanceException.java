package com.brainridge.avbank.exception;

import java.math.BigDecimal;

public class InsufficientSenderBalanceException extends RuntimeException{
    public InsufficientSenderBalanceException(String payerEmail, BigDecimal balance, BigDecimal amount) {
        super("Sender's balance (" + balance + ") is less than the transaction amount (" + amount + ") for email: " + payerEmail);
    }
}
