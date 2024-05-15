package com.brandbank.transactions.domain.validator.register;

public interface ValidationProcess {
    public void addError(String field, String error);
}
