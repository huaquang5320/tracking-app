package com.thomas.trackingapp.domain.finance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FinanceTransactionRepository extends JpaRepository<FinanceTransaction, Long> {

    Optional<FinanceTransaction> findByIdAndUserId(Long id, Long userId);

    List<FinanceTransaction> findByUserIdAndLoggedDateBetweenOrderByLoggedDateDesc(
            Long userId, LocalDate from, LocalDate to);

    @Query("SELECT f.category, SUM(f.amount) FROM FinanceTransaction f " +
           "WHERE f.userId = :userId AND f.type = :type " +
           "AND f.loggedDate BETWEEN :from AND :to " +
           "GROUP BY f.category")
    List<Object[]> sumByCategory(
            @Param("userId") Long userId,
            @Param("type") TransactionType type,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to);
}
