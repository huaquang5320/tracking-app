package com.thomas.trackingapp.domain.weight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeightLogRepository extends JpaRepository<WeightLog, Long> {

    Optional<WeightLog> findByUserIdAndLoggedDate(Long userId, LocalDate loggedDate);

    List<WeightLog> findByUserIdAndLoggedDateBetweenOrderByLoggedDateDesc(
            Long userId,
            LocalDate from,
            LocalDate to
    );
}
