package com.thomas.trackingapp.domain.workout.dto;

import com.thomas.trackingapp.domain.workout.WorkoutExercise;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class WorkoutExerciseResponse {

    private Long id;
    private String name;
    private Integer sets;
    private Integer reps;
    private BigDecimal weightKg;
    private BigDecimal rpe;
    private Integer restSeconds;
    private String note;

    public static WorkoutExerciseResponse from(WorkoutExercise entity) {
        return WorkoutExerciseResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .sets(entity.getSets())
                .reps(entity.getReps())
                .weightKg(entity.getWeightKg())
                .rpe(entity.getRpe())
                .restSeconds(entity.getRestSeconds())
                .note(entity.getNote())
                .build();
    }
}
