package com.brandbank.transactions.application.response;

import com.brandbank.transactions.domain.model.entity.Transaction;
import com.brandbank.transactions.domain.model.entity.enums.MovimentationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(String type, BigDecimal value, LocalDateTime time) {
    public TransactionResponse(Transaction transaction) {
            this(MovimentationType.isDEBT(transaction.getMovement()) ? "DEBITO" : "CREDITO",
                    MovimentationType.isDEBT(transaction.getMovement())
                    ? transaction.getMovementValue().multiply(BigDecimal.ZERO.subtract(BigDecimal.ONE))
                    : transaction.getMovementValue(),
                transaction.getCreatedAt()
            );
    }
}
