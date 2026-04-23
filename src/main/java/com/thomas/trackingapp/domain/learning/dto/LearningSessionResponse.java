package com.thomas.trackingapp.domain.learning.dto;

import com.thomas.trackingapp.domain.learning.LearningSession;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class LearningSessionResponse {

    private Long id;
    private String topic;
    private String source;
    private Integer durationMin;
    private String note;
    private LocalDate loggedDate;
    private LocalDateTime createdAt;

    public static LearningSessionResponse from(LearningSession entity) {
        return LearningSessionResponse.builder()
                .id(entity.getId())
                .topic(entity.getTopic())
                .source(entity.getSource())
                .durationMin(entity.getDurationMin())
                .note(entity.getNote())
                .loggedDate(entity.getLoggedDate())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
