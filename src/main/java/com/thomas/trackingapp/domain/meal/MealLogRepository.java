package com.thomas.trackingapp.domain.meal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    // Recent unique meals theo food_name — dùng cho quick-log chips
    // Postgres-specific: DISTINCT ON lấy 1 row mới nhất / mỗi food_name
    @Query(value = """
            SELECT * FROM (
              SELECT DISTINCT ON (food_name) *
              FROM meal_logs
              WHERE user_id = :userId
                AND food_name IS NOT NULL
                AND food_name <> ''
              ORDER BY food_name, created_at DESC
            ) AS sub
            ORDER BY created_at DESC
            LIMIT :limit
            """, nativeQuery = true)
    List<MealLog> findRecentDistinctByFoodName(
            @Param("userId") Long userId,
            @Param("limit") int limit);

}