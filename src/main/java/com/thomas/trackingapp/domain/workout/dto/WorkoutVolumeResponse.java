package com.thomas.trackingapp.domain.workout.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class WorkoutVolumeResponse {
    private Long sessionId;
    private BigDecimal totalVolume;
}
