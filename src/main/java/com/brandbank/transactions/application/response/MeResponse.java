package com.brandbank.transactions.application.response;

import java.math.BigDecimal;

public record MeResponse(String accountCode, BigDecimal balance) {
}
