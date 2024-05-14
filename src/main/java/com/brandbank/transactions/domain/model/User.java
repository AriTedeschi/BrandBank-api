package com.brandbank.transactions.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {
    @Id
    private Long id;
    private String accountCode;
    private String email;
    private String name;
    private Integer age;
    private String address;
}