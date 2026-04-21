package com.thomas.trackingapp.domain.meal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thomas.trackingapp.common.exception.ResourceNotFoundException;
import com.thomas.trackingapp.domain.meal.dto.MealDailySummary;
import com.thomas.trackingapp.domain.meal.dto.MealLogRequest;
import com.thomas.trackingapp.domain.meal.dto.MealLogResponse;
import com.thomas.trackingapp.domain.meal.dto.MealLogUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealLogService {

    private final MealLogRepository mealLogRepository;

    // 1. Create — save entity, trả MealLogResponse
    public MealLogResponse createMeal(Long userId, MealLogRequest request) {
        // map request → entity
        MealLog mealLog = new MealLog();
        mealLog.setUserId(userId);
        mealLog.setMealType(request.getMealType());
        mealLog.setFoodName(request.getFoodName());
        mealLog.setCalories(request.getCalories());
        mealLog.setProteinG(request.getProteinG());
        mealLog.setCarbG(request.getCarbG());
        mealLog.setFatG(request.getFatG());
        mealLog.setLoggedDate(request.getLoggedDate());
        mealLog.setNote(request.getNote());
        // save
        MealLog saved = mealLogRepository.save(mealLog);
        // trả response
        return MealLogResponse.from(saved);
    }

    // 2. GetByDate — lấy list meals theo ngày, trả List<MealLogResponse>
    public List<MealLogResponse> getMealsByDate(Long userId, LocalDate date) {
        // query repository
        List<MealLog> meals = mealLogRepository.findByUserIdAndLoggedDateOrderByCreatedAtAsc(userId, date);
        // map sang response
        return meals.stream().map(MealLogResponse::from).collect(Collectors.toList());
    }

    // 3. Update — tìm record, verify ownership, update fields, save
    public MealLogResponse updateMeal(Long userId, Long id, MealLogUpdateRequest request) {
        // findByIdAndUserId → 404 nếu không có
        MealLog mealLog = mealLogRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Meal log not found"));
        // update fields (trừ loggedDate)
        mealLog.setMealType(request.getMealType());
        mealLog.setFoodName(request.getFoodName());
        mealLog.setCalories(request.getCalories());
        mealLog.setProteinG(request.getProteinG());
        mealLog.setCarbG(request.getCarbG());
        mealLog.setFatG(request.getFatG());
        mealLog.setNote(request.getNote());
        // save
        MealLog saved = mealLogRepository.save(mealLog);
        // trả response
        return MealLogResponse.from(saved);
    }

    // 4. Delete — tìm record, verify ownership, xóa
    public void deleteMeal(Long userId, Long id) {
        // findByIdAndUserId → 404 nếu không có
        MealLog mealLog = mealLogRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Meal log not found"));
        // delete
        mealLogRepository.delete(mealLog);
    }

    // 5. DailySummary — lấy list meals, tính tổng
    public MealDailySummary getDailySummary(Long userId, LocalDate date) {
        // getMealsByDate
        List<MealLogResponse> meals = getMealsByDate(userId, date);
        // tính totalCalories, totalProtein, totalCarb, totalFat bằng stream
        int totalCalories = meals.stream()
                .mapToInt(MealLogResponse::getCalories)
                .sum();
        BigDecimal totalProtein = meals.stream()
                .map(MealLogResponse::getProteinG)
                .filter(v -> v != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCarb = meals.stream()
                .map(MealLogResponse::getCarbG)
                .filter(v -> v != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalFat = meals.stream()
                .map(MealLogResponse::getFatG)
                .filter(v -> v != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new MealDailySummary(totalCalories, totalProtein, totalCarb, totalFat);
    }
}
