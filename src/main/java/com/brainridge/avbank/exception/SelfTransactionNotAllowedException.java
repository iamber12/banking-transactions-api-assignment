package com.brainridge.avbank.exception;

public class SelfTransactionNotAllowedException extends RuntimeException {
    public SelfTransactionNotAllowedException(String email) {
        super("Transaction not allowed: Payer and payee cannot have the same email ( " + email + " )");
    }
}
