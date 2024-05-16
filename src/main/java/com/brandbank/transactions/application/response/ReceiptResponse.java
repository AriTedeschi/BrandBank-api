package com.brandbank.transactions.application.response;

import java.math.BigDecimal;
import java.util.List;

public record ReceiptResponse(String accountCode, BigDecimal bigDecimal, List<TransactionResponse> transactions) {
}
