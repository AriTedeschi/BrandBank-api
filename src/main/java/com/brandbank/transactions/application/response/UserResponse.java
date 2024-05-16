package com.brandbank.transactions.application.response;

import java.math.BigDecimal;

public record UserResponse(String accountCode, String email, String name, BigDecimal balance) {
}
