package com.thomas.trackingapp.domain.workout.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class WorkoutExerciseRequest {

    @NotBlank(message = "Exercise name is required")
    private String name;

    @Min(value = 0, message = "Sets must be at least 0")
    private Integer sets;

    @Min(value = 0, message = "Reps must be at least 0")
    private Integer reps;

    @DecimalMin(value = "0.0", message = "Weight must be at least 0")
    private BigDecimal weightKg;

    @DecimalMin(value = "1.0", message = "RPE must be at least 1")
    @DecimalMax(value = "10.0", message = "RPE must be at most 10")
    private BigDecimal rpe;

    @Min(value = 0, message = "Rest seconds must be at least 0")
    private Integer restSeconds;

    private String note;
}
