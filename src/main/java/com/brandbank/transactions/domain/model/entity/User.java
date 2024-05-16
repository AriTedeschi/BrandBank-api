package com.brandbank.transactions.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String accountCode;
    private String password;
    private UserRole role;
    private String email;
    private String name;
    private Integer age;
    private String address;
    private LocalDateTime createdAt;
}