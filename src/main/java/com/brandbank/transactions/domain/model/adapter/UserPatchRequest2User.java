package com.brandbank.transactions.domain.model.adapter;

import com.brandbank.transactions.domain.model.entity.User;
import com.brandbank.transactions.domain.model.request.UserPatchRequest;

import static java.util.Objects.isNull;

public class UserPatchRequest2User {
    private User instance;
    public UserPatchRequest2User(User user){
        this.instance = user;
    }

    public User patch(UserPatchRequest request){
        this.instance.setName(isNull(request.name()) ?  this.instance.getName() : request.name());
        this.instance.setAge(isNull(request.age()) ?  this.instance.getAge() : request.age());
        this.instance.setAddress(isNull(request.address()) ?  this.instance.getAddress() : request.address());
        return instance;
    }
}
