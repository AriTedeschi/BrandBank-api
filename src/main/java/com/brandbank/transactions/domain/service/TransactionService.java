package com.brandbank.transactions.domain.service;

import com.brandbank.transactions.application.response.UserResponse;

import java.math.BigDecimal;

public interface TransactionService {
    public UserResponse debit(String accountCode, BigDecimal value);
    public UserResponse credt(String accountCode, BigDecimal value);
}
