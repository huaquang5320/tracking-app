package com.thomas.trackingapp.domain.weight.dto;

import com.thomas.trackingapp.domain.weight.WeightLog;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class WeightLogResponse {

    private Long id;
    private BigDecimal weightKg;
    private LocalDate loggedDate;
    private String note;
    private LocalDateTime createdAt;

    public static WeightLogResponse from(WeightLog entity) {
        return WeightLogResponse.builder()
                .id(entity.getId())
                .weightKg(entity.getWeightKg())
                .loggedDate(entity.getLoggedDate())
                .note(entity.getNote())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
