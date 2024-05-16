package com.brandbank.transactions.domain.repository;

import com.brandbank.transactions.domain.model.entity.Custody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustodyRepository extends JpaRepository<Custody, String> {
    @Query(
            value = "SELECT * FROM PUBLIC.TB_CUSTODY u WHERE u.id = ?1",
            nativeQuery = true)
    Optional<Custody> getCustody(String uuid);

    @Query(
            value = "SELECT * FROM PUBLIC.TB_CUSTODY u WHERE u.user_id = ?1",
            nativeQuery = true)
    Optional<Custody> getCustodyOfOwner(String uuid);
}
