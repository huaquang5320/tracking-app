package com.thomas.trackingapp.domain.meal.dto;

import com.thomas.trackingapp.domain.meal.MealLog;
import com.thomas.trackingapp.domain.meal.MealType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class MealRecentResponse {

    private MealType mealType;
    private String foodName;
    private Integer calories;
    private BigDecimal proteinG;
    private BigDecimal carbG;
    private BigDecimal fatG;

    public static MealRecentResponse from(MealLog entity) {
        return MealRecentResponse.builder()
                .mealType(entity.getMealType())
                .foodName(entity.getFoodName())
                .calories(entity.getCalories())
                .proteinG(entity.getProteinG())
                .carbG(entity.getCarbG())
                .fatG(entity.getFatG())
                .build();
    }
}
