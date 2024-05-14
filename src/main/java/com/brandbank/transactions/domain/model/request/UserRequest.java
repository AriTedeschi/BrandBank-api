package com.brandbank.transactions.domain.model.request;

public record UserRequest(String email, String name, Integer age, String address) {
}
