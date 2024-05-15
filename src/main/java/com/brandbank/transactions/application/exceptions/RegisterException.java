package com.brandbank.transactions.application.exceptions;

public class RegisterException extends RuntimeException{
    public RegisterException(String errorMessage) {
        super(errorMessage);
    }
}
