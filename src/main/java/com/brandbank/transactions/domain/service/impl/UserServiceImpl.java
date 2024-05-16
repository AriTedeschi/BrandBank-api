package com.brandbank.transactions.domain.service.impl;

import com.brandbank.transactions.application.response.UserResponse;
import com.brandbank.transactions.domain.model.adapter.User2UserResponse;
import com.brandbank.transactions.domain.model.adapter.UserPatchRequest2User;
import com.brandbank.transactions.domain.model.adapter.UserRequest2User;
import com.brandbank.transactions.domain.model.entity.Custody;
import com.brandbank.transactions.domain.model.entity.User;
import com.brandbank.transactions.domain.model.request.UserPatchRequest;
import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.domain.repository.UserRepository;
import com.brandbank.transactions.domain.service.CustodyService;
import com.brandbank.transactions.domain.service.UserService;
import com.brandbank.transactions.domain.validator.register.PatchValidator;
import com.brandbank.transactions.domain.validator.register.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private CustodyService custodyService;

    private Random random = new Random();

    @Override
    public UserResponse register(UserRequest request){
        new RegisterValidator(request).validate();
        repository.findByEmail(request.email()).ifPresent(
           v -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registred");}
        );

        User user = new UserRequest2User(request).getInstance();
        user.setAccountCode(generateAccountCode());
        user.setCreatedAt(LocalDateTime.now());
        User savedUser = repository.save(user);
        Custody custody = custodyService.register(savedUser);
        savedUser.setCustody(custody);
        savedUser = repository.save(savedUser);

        return new User2UserResponse(savedUser,custody.getBalance()).getInstance();
    }

    @Override
    public UserResponse edit(String accountCode, UserPatchRequest request) {
        new PatchValidator(request).validate();

        AtomicReference<UserResponse> response = new AtomicReference<>();
        repository.findByAccountCode(accountCode).ifPresentOrElse(
                user -> {
                    user = new UserPatchRequest2User(user).patch(request);
                    BigDecimal balance = custodyService.getCustodyOfOwner(user.getId()).getBalance();
                    response.set(new User2UserResponse(repository.save(user),balance).getInstance());
                },
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
                }
        );
        return response.get();
    }

    @Override
    public UserResponse getUser(String accountCode) {
        AtomicReference<UserResponse> response = new AtomicReference<>();
        repository.findByAccountCode(accountCode).ifPresentOrElse(
                user -> {
                    BigDecimal balance = custodyService.getCustodyOfOwner(user.getId()).getBalance();
                    response.set(new User2UserResponse(user,balance).getInstance());
                    },
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
                }
        );
        return response.get();
    }

    @Override
    public User get(String accountCode) {
        return repository.findByAccountCode(accountCode).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public String generateAccountCode(){
        String accountCode = "";
        do {
            int upper = random.nextInt(100000);
            accountCode = String.format("%05d.%02d", upper, 00);
        } while (repository.findByAccountCode(accountCode).isPresent());
        return accountCode;
    }
}
