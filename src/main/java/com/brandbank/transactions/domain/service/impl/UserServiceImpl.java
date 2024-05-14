package com.brandbank.transactions.domain.service.impl;

import com.brandbank.transactions.domain.model.User;
import com.brandbank.transactions.domain.model.adapter.UserRequest2User;
import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.domain.model.response.UserResponse;
import com.brandbank.transactions.domain.repository.UserRepository;
import com.brandbank.transactions.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserResponse register(UserRequest request) {
        //TODO Request validation
        User user = new UserRequest2User(request).getInstance();
        repository.save(user);
        return null;
    }

    @Override
    public UserResponse edit(UserRequest request) {
        //TODO
        return null;
    }

    @Override
    public UserResponse getUser(String accountCode) {
        //TODO
        return null;
    }
}
