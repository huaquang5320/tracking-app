package com.thomas.trackingapp.domain.goal.dto;

import com.thomas.trackingapp.domain.goal.UserGoal;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class UserGoalResponse {

    private Long id;
    private Integer targetCalories;
    private BigDecimal targetProteinG;
    private BigDecimal targetCarbG;
    private BigDecimal targetFatG;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserGoalResponse from(UserGoal entity) {
        return UserGoalResponse.builder()
                .id(entity.getId())
                .targetCalories(entity.getTargetCalories())
                .targetProteinG(entity.getTargetProteinG())
                .targetCarbG(entity.getTargetCarbG())
                .targetFatG(entity.getTargetFatG())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
