package com.thomas.trackingapp.domain.meal.dto;

import com.thomas.trackingapp.domain.meal.MealLog;
import com.thomas.trackingapp.domain.meal.MealType;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class MealLogResponse {

    private Long id;
    private MealType mealType;
    private String foodName;
    private Integer calories;
    private BigDecimal proteinG;
    private BigDecimal carbG;
    private BigDecimal fatG;
    private LocalDate loggedDate;
    private String note;
    private LocalDateTime createdAt;

    public static MealLogResponse from(MealLog entity) {
        return MealLogResponse.builder()
                .id(entity.getId())
                .mealType(entity.getMealType())
                .foodName(entity.getFoodName())
                .calories(entity.getCalories())
                .proteinG(entity.getProteinG())
                .carbG(entity.getCarbG())
                .fatG(entity.getFatG())
                .loggedDate(entity.getLoggedDate())
                .note(entity.getNote())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
