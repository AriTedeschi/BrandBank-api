package com.brandbank.transactions.domain.repository;

import com.brandbank.transactions.domain.model.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query(
            value = "SELECT * FROM PUBLIC.TB_TRANSACTION t WHERE t.custody_id = ?1",
            nativeQuery = true)
    Page<Transaction> findByCustodyID(String custody_id, Pageable pageRequest);
}
