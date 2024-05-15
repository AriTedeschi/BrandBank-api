package com.brandbank.transactions.domain.repository;

import com.brandbank.transactions.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(
            value = "SELECT * FROM PUBLIC.USER u WHERE u.account_code = ?1",
            nativeQuery = true)
    Optional<User> findByAccountCode(String accountCode);
}
