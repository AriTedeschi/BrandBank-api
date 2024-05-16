package com.brandbank.transactions.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "TB_CUSTODY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Custody {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private BigDecimal balance;
    @Column(name="balance_limit")
    private BigDecimal limit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @OneToMany(mappedBy="custody")
    private Set<Transaction> transactions;
}
