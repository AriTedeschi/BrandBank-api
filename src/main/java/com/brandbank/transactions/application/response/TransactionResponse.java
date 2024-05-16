package com.brandbank.transactions.application.response;

import com.brandbank.transactions.domain.model.entity.Transaction;
import com.brandbank.transactions.domain.model.entity.enums.MovimentationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(String type, BigDecimal value, LocalDateTime time) {
    public TransactionResponse(Transaction transaction) {
            this(MovimentationType.isDEBT(transaction.getMoviment()) ? "DEBITO" : "CREDITO",
                    MovimentationType.isDEBT(transaction.getMoviment())
                    ? transaction.getValue().multiply(BigDecimal.ZERO.subtract(BigDecimal.ONE))
                    : transaction.getValue(),
                transaction.getCreatedAt()
            );
    }
}
