package com.brandbank.transactions.domain.service;

import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.domain.model.response.UserResponse;

public interface UserService {
    public UserResponse register(UserRequest request);

    public UserResponse edit(UserRequest request);

    public UserResponse getUser(String accountCode);
}
