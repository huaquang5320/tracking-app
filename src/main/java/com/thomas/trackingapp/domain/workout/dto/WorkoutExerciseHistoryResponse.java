package com.thomas.trackingapp.domain.workout.dto;

import com.thomas.trackingapp.domain.workout.WorkoutExercise;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class WorkoutExerciseHistoryResponse {

    private LocalDateTime date;
    private String name;
    private Integer sets;
    private Integer reps;
    private BigDecimal weightKg;
    private BigDecimal rpe;

    public static WorkoutExerciseHistoryResponse from(WorkoutExercise entity) {
        return WorkoutExerciseHistoryResponse.builder()
                .date(entity.getSession().getStartedAt())
                .name(entity.getName())
                .sets(entity.getSets())
                .reps(entity.getReps())
                .weightKg(entity.getWeightKg())
                .rpe(entity.getRpe())
                .build();
    }
}
