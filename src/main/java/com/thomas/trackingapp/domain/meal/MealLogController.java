package com.thomas.trackingapp.domain.meal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thomas.trackingapp.domain.meal.dto.MealDailySummary;
import com.thomas.trackingapp.domain.meal.dto.MealLogRequest;
import com.thomas.trackingapp.domain.meal.dto.MealLogResponse;
import com.thomas.trackingapp.domain.meal.dto.MealLogUpdateRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/meal-logs")
@RequiredArgsConstructor
public class MealLogController {

    private final MealLogService mealLogService;

    // POST /api/meals
    @PostMapping
    public ResponseEntity<MealLogResponse> createMeal(@Valid @RequestBody MealLogRequest request) {
        Long userId = 1L; // hardcode tạm
        MealLogResponse response = mealLogService.createMeal(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/meals?date=2024-01-15
    @GetMapping
    public ResponseEntity<List<MealLogResponse>> getMealsByDate(
            @RequestParam LocalDate date) {
        Long userId = 1L;
        List<MealLogResponse> meals = mealLogService.getMealsByDate(userId, date);
        return ResponseEntity.ok(meals);
    }

    // GET /api/meals/summary?date=2024-01-15
    @GetMapping("/summary")
    public ResponseEntity<MealDailySummary> getDailySummary(@RequestParam LocalDate date) {
        Long userId = 1L;
        MealDailySummary summary = mealLogService.getDailySummary(userId, date);
        return ResponseEntity.ok(summary);
    }

    // PUT /api/meals/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MealLogResponse> updateMeal(@PathVariable Long id,
            @Valid @RequestBody MealLogUpdateRequest request) {
        Long userId = 1L;
        MealLogResponse response = mealLogService.updateMeal(userId, id, request);
        return ResponseEntity.ok(response);
    }

    // DELETE /api/meals/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeal(@PathVariable Long id) {
        Long userId = 1L;
        mealLogService.deleteMeal(userId, id);
        return ResponseEntity.noContent().build();
    }
}
