package com.thomas.trackingapp.domain.meal.dto;

import java.math.BigDecimal;

import com.thomas.trackingapp.domain.meal.MealType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MealLogUpdateRequest {

    @NotNull(message = "Meal type is required")
    private MealType mealType;

    private String foodName;

    @NotNull(message = "Calories is required")
    @Min(value = 0, message = "Calories must be at least 0")
    private Integer calories;

    @DecimalMin(value = "0.0", message = "Protein must be at least 0")
    private BigDecimal proteinG;

    @DecimalMin(value = "0.0", message = "Carb must be at least 0")
    private BigDecimal carbG;

    @DecimalMin(value = "0.0", message = "Fat must be at least 0")
    private BigDecimal fatG;

    private String note;
    // Không có logged_date
}