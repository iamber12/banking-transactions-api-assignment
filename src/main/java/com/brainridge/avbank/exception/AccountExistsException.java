package com.brainridge.avbank.exception;

public class AccountExistsException extends RuntimeException {
    public AccountExistsException(String email) {
        super("An account already exists with the email: " + email);
    }
}
