package com.brandbank.transactions.domain.repository;

import com.brandbank.transactions.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(
            value = "SELECT * FROM PUBLIC.TB_USER u WHERE u.account_code = ?1",
            nativeQuery = true)
    Optional<User> findByAccountCode(String accountCode);

    @Query(
            value = "SELECT * FROM PUBLIC.TB_USER u WHERE u.email = ?1",
            nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(
            value = "SELECT * FROM PUBLIC.TB_USER u WHERE u.name like '%?1%'",
            nativeQuery = true)
    List<User> findByName(String name);
}
