package com.thomas.trackingapp.domain.workout.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class WorkoutSessionRequest {

    @NotNull(message = "Started at is required")
    private LocalDateTime startedAt;

    @NotNull(message = "Ended at is required")
    private LocalDateTime endedAt;

    private String note;

    @NotEmpty(message = "Exercises cannot be empty")
    private List<@Valid WorkoutExerciseRequest> exercises;
}
