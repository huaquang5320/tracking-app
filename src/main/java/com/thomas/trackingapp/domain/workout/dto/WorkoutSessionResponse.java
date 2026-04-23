package com.thomas.trackingapp.domain.workout.dto;

import com.thomas.trackingapp.domain.workout.WorkoutSession;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class WorkoutSessionResponse {

    private Long id;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String note;
    private LocalDateTime createdAt;
    private List<WorkoutExerciseResponse> exercises;

    public static WorkoutSessionResponse from(WorkoutSession entity) {
        return WorkoutSessionResponse.builder()
                .id(entity.getId())
                .startedAt(entity.getStartedAt())
                .endedAt(entity.getEndedAt())
                .note(entity.getNote())
                .createdAt(entity.getCreatedAt())
                .exercises(entity.getExercises().stream()
                        .map(WorkoutExerciseResponse::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
