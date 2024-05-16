package com.brandbank.transactions.domain.model.adapter;

import com.brandbank.transactions.domain.model.entity.User;
import com.brandbank.transactions.application.response.UserResponse;

public class User2UserResponse {
    private UserResponse instance;
    public User2UserResponse(User user){
        this.instance = new UserResponse(user.getAccountCode(), user.getEmail(), user.getName());
    }

    public UserResponse getInstance(){ return this.instance;}
}
