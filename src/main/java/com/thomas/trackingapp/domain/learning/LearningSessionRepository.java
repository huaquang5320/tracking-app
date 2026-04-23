package com.thomas.trackingapp.domain.learning;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LearningSessionRepository extends JpaRepository<LearningSession, Long> {

    Optional<LearningSession> findByIdAndUserId(Long id, Long userId);

    List<LearningSession> findByUserIdAndLoggedDateBetweenOrderByLoggedDateDesc(
            Long userId, LocalDate from, LocalDate to);

    // Tổng minutes theo topic trong khoảng thời gian
    @Query("SELECT l.topic, SUM(l.durationMin) FROM LearningSession l " +
           "WHERE l.userId = :userId AND l.loggedDate BETWEEN :from AND :to " +
           "GROUP BY l.topic ORDER BY SUM(l.durationMin) DESC")
    List<Object[]> sumDurationByTopic(
            @Param("userId") Long userId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to);
}
