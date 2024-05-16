package com.brandbank.transactions.domain.service;

import com.brandbank.transactions.domain.model.request.UserPatchRequest;
import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.application.response.UserResponse;

public interface UserService {
    public UserResponse register(UserRequest request);

    public UserResponse edit(String accountCode, UserPatchRequest request);

    public UserResponse getUser(String accountCode);
}
