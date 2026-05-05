package com.thomas.trackingapp.domain.goal.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class UserGoalRequest {

    @NotNull(message = "Target calories is required")
    @Min(value = 800, message = "Target calories must be at least 800")
    @Max(value = 6000, message = "Target calories must be under 6000")
    private Integer targetCalories;

    @NotNull(message = "Target protein is required")
    @DecimalMin(value = "0.0", message = "Target protein must be non-negative")
    @DecimalMax(value = "500.0", message = "Target protein must be under 500g")
    private BigDecimal targetProteinG;

    @NotNull(message = "Target carb is required")
    @DecimalMin(value = "0.0", message = "Target carb must be non-negative")
    @DecimalMax(value = "1000.0", message = "Target carb must be under 1000g")
    private BigDecimal targetCarbG;

    @NotNull(message = "Target fat is required")
    @DecimalMin(value = "0.0", message = "Target fat must be non-negative")
    @DecimalMax(value = "300.0", message = "Target fat must be under 300g")
    private BigDecimal targetFatG;
}
