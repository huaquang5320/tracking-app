package com.thomas.trackingapp.domain.meal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealLogRepository extends JpaRepository<MealLog, Long> {

    // Xem tất cả meals theo ngày
    List<MealLog> findByUserIdAndLoggedDateOrderByCreatedAtAsc(
            Long userId, LocalDate loggedDate);

    // Xem theo ngày + meal type (filter sáng/trưa/tối)
    List<MealLog> findByUserIdAndLoggedDateAndMealType(
            Long userId, LocalDate loggedDate, MealType mealType);

    // Check ownership trước khi update/delete
    Optional<MealLog> findByIdAndUserId(Long id, Long userId);

}