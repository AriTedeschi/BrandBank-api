package com.brandbank.transactions.domain.service;

import com.brandbank.transactions.application.response.TransactionResponse;
import com.brandbank.transactions.application.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface TransactionService {
    public UserResponse debit(String accountCode, BigDecimal value);
    public UserResponse credt(String accountCode, BigDecimal value);
    public Page<TransactionResponse> list(String accountCode, Pageable pageRequest);
}
