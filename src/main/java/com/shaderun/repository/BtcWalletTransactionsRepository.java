package com.shaderun.repository;

import com.shaderun.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalUnit;
import java.util.List;

@Repository
public interface BtcWalletTransactionsRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT t FROM TransactionEntity t WHERE t.dateTime BETWEEN :start AND :end")
    List<TransactionEntity> findByDateTimeBetween(@Param("start") OffsetDateTime start, @Param("end") OffsetDateTime end);

    TransactionEntity findByDateTime(OffsetDateTime end);

    List<TransactionEntity> findAll(Specification<TransactionEntity> spec);


}
