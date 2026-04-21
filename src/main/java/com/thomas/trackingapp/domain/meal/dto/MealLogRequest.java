package com.thomas.trackingapp.domain.meal.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.thomas.trackingapp.domain.meal.MealType;

@Getter
@NoArgsConstructor
public class MealLogRequest {

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

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate loggedDate;

    private String note;
}
