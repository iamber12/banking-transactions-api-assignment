package com.brainridge.avbank.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long accountId) {
        super("Account with ID " + accountId + " not found");
    }
    public AccountNotFoundException(String email) {
        super("Account with email " + email + " not found");
    }
}
