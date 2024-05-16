package com.brandbank.transactions.domain.model.entity;

import com.brandbank.transactions.domain.model.entity.enums.MovimentationType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_TRANSACTION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private BigDecimal movementValue;

    private MovimentationType movement;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="custody_id", nullable=false)
    private Custody custody;
}
