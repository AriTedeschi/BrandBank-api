package com.brandbank.transactions.domain.model.adapter;

import com.brandbank.transactions.domain.model.User;
import com.brandbank.transactions.domain.model.request.UserRequest;

public class UserRequest2User{
    private User instance;
    public UserRequest2User(UserRequest request){
        this.instance = instance.builder().email(request.email())
                .name(request.name())
                .age(request.age())
                .address(request.address()).build();
    }

    public User getInstance(){ return instance;}
}
