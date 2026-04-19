package com.thomas.trackingapp.domain.weight.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class WeightLogRequest {

    @NotNull(message = "Weight is required")
    @DecimalMin(value = "20.0", message = "Weight must be at least 20kg")
    @DecimalMax(value = "300.0", message = "Weight must be under 300kg")
    private BigDecimal weightKg;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate loggedDate;

    private String note;
}
