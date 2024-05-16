package com.brandbank.transactions.domain.repository;

import com.brandbank.transactions.domain.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query(
            value = "SELECT * FROM PUBLIC.TB_TRANSACTION t WHERE t.custody_id = ?1",
            nativeQuery = true)
    List<Transaction> get(String custody_uuid);
}
