package com.brandbank.transactions.domain.service.impl;

import com.brandbank.transactions.domain.model.entity.Custody;
import com.brandbank.transactions.domain.model.entity.User;
import com.brandbank.transactions.domain.repository.CustodyRepository;
import com.brandbank.transactions.domain.service.CustodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class CustodyServiceImpl implements CustodyService {
    @Autowired
    private CustodyRepository repository;

    @Override
    public Custody register(User user) {
        Custody custody = new Custody();
        custody.setBalance(BigDecimal.ZERO);
        custody.setLimit(BigDecimal.ZERO);
        custody.setOwner(user);
        return repository.save(custody);
    }

    public Custody getCustody(String uuid){
        return repository.getCustody(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Custody not found!"));
    }

    public Custody getCustodyOfOwner(String uuid){
        return repository.getCustodyOfOwner(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Custody not found!"));
    }

    @Override
    public void credit(String owner_uuid, BigDecimal value) {
        Custody custody = getCustodyOfOwner(owner_uuid);
        BigDecimal balance = custody.getBalance();
        balance = balance.add(value);
        custody.setBalance(balance);
        repository.save(custody);
    }

    @Override
    public void debt(String owner_uuid, BigDecimal value) {
        Custody custody = getCustodyOfOwner(owner_uuid);
        BigDecimal balance = custody.getBalance();
        BigDecimal limit = custody.getLimit();

        if(balance.subtract(value).compareTo(limit) < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Insuficient balance to debt value");

        balance = balance.subtract(value);
        custody.setBalance(balance);
        repository.save(custody);
    }
}
