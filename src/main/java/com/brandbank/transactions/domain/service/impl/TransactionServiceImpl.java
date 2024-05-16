package com.brandbank.transactions.domain.service.impl;

import com.brandbank.transactions.application.response.TransactionResponse;
import com.brandbank.transactions.application.response.UserResponse;
import com.brandbank.transactions.domain.model.entity.Transaction;
import com.brandbank.transactions.domain.model.entity.enums.MovimentationType;
import com.brandbank.transactions.domain.repository.TransactionRepository;
import com.brandbank.transactions.domain.service.CustodyService;
import com.brandbank.transactions.domain.service.TransactionService;
import com.brandbank.transactions.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if(value.compareTo(BigDecimal.ZERO) < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Dont inform negative values");

        String user_uuid = userService.get(accountCode).getId();
        custodyService.debt(user_uuid,value);

        Transaction transaction = new Transaction();
        transaction.setMovement(MovimentationType.DEBT);
        transaction.setMovementValue(value);
        transaction.setCustody(custodyService.getCustodyOfOwner(user_uuid));
        transaction.setCreatedAt(LocalDateTime.now());
        repository.save(transaction);

        return userService.getUser(accountCode);
    }

    @Override
    public UserResponse credt(String accountCode, BigDecimal value) {
        if(value.compareTo(BigDecimal.ZERO) < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Dont inform negative values");

        String user_uuid = userService.get(accountCode).getId();
        custodyService.credit(user_uuid,value);

        Transaction transaction = new Transaction();
        transaction.setMovement(MovimentationType.CREDT);
        transaction.setMovementValue(value);
        transaction.setCustody(custodyService.getCustodyOfOwner(user_uuid));
        transaction.setCreatedAt(LocalDateTime.now());
        repository.save(transaction);

        return userService.getUser(accountCode);
    }

    @Override
    public Page<TransactionResponse> list(String accountCode, Pageable pageRequest) {
        String user_uuid = userService.get(accountCode).getId();
        String custody_uuid = custodyService.getCustodyOfOwner(user_uuid).getId();

        Page<TransactionResponse> page = repository.findByCustodyID(custody_uuid,pageRequest).map(TransactionResponse::new);
        return page;
    }
}
