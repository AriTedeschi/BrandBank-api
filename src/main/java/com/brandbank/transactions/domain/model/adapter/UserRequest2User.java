package com.brandbank.transactions.domain.model.adapter;

import com.brandbank.transactions.domain.model.entity.User;
import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.domain.model.entity.enums.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserRequest2User{
    private User instance;
    public UserRequest2User(UserRequest request){
        this.instance = instance.builder().email(request.email())
                .name(request.name())
                .age(request.age())
                .password(new BCryptPasswordEncoder().encode(request.password()))
                .role(UserRole.USER)
                .address(request.address()).build();
    }

    public User getInstance(){ return instance;}
}
