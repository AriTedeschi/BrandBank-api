package com.brandbank.transactions.domain.service.impl;

import com.brandbank.transactions.domain.model.User;
import com.brandbank.transactions.domain.model.adapter.User2UserResponse;
import com.brandbank.transactions.domain.model.adapter.UserRequest2User;
import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.domain.model.response.UserResponse;
import com.brandbank.transactions.domain.repository.UserRepository;
import com.brandbank.transactions.domain.service.UserService;
import com.brandbank.transactions.domain.validator.register.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    private Random random = new Random();

    @Override
    public UserResponse register(UserRequest request){
        RegisterValidator validator = new RegisterValidator(request);
        validator.validate();

        User user = new UserRequest2User(request).getInstance();
        user.setAccountCode(generateAccountCode());
        user.setCreatedAt(LocalDateTime.now());
        return new User2UserResponse(repository.save(user)).getInstance();
    }

    @Override
    public UserResponse edit(UserRequest request) {
        //TODO
        return null;
    }

    @Override
    public UserResponse getUser(String accountCode) {
        AtomicReference<UserResponse> response = new AtomicReference<>();
        repository.findByAccountCode(accountCode).ifPresentOrElse(
                user -> response.set(new User2UserResponse(user).getInstance()),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
                }
        );
        return response.get();
    }

    public String generateAccountCode(){
        int upper = random.nextInt(100000);
        return String.format("%05d.%02d", upper, 00);
    }
}
