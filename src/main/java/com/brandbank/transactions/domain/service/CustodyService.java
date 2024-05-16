package com.brandbank.transactions.domain.service;

import com.brandbank.transactions.domain.model.entity.Custody;
import com.brandbank.transactions.domain.model.entity.User;

import java.math.BigDecimal;

public interface CustodyService {
    public Custody register(User user);

    public Custody getCustody(String uuid);
    public Custody getCustodyOfOwner(String uuid);

    public void credit(String owner_uuid, BigDecimal value);

    public void debt(String owner_uuid, BigDecimal value);
}
