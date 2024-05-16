package com.brandbank.transactions.domain.model.request;

public record UserRequest(String email, String name, String password, Integer age, String address) {
}
