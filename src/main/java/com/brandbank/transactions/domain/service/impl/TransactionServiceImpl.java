package com.brandbank.transactions.domain.service.impl;

import com.brandbank.transactions.application.response.UserResponse;
import com.brandbank.transactions.domain.model.entity.Transaction;
import com.brandbank.transactions.domain.model.entity.enums.MovimentationType;
import com.brandbank.transactions.domain.repository.TransactionRepository;
import com.brandbank.transactions.domain.service.CustodyService;
import com.brandbank.transactions.domain.service.TransactionService;
import com.brandbank.transactions.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private CustodyService custodyService;

    @Override
    public UserResponse debit(String accountCode, BigDecimal value) {
        String user_uuid = userService.get(accountCode).getId();
        custodyService.debt(user_uuid,value);

        Transaction transaction = new Transaction();
        transaction.setMoviment(MovimentationType.DEBT);
        transaction.setValue(value);
        transaction.setCustody(custodyService.getCustodyOfOwner(user_uuid));
        transaction.setCreatedAt(LocalDateTime.now());
        repository.save(transaction);

        return userService.getUser(accountCode);
    }

    @Override
    public UserResponse credt(String accountCode, BigDecimal value) {
        String user_uuid = userService.get(accountCode).getId();
        custodyService.credit(user_uuid,value);

        Transaction transaction = new Transaction();
        transaction.setMoviment(MovimentationType.CREDT);
        transaction.setValue(value);
        transaction.setCustody(custodyService.getCustodyOfOwner(user_uuid));
        transaction.setCreatedAt(LocalDateTime.now());
        repository.save(transaction);

        return userService.getUser(accountCode);
    }
}
