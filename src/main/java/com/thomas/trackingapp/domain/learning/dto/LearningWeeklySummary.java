package com.thomas.trackingapp.domain.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class LearningWeeklySummary {
    private int totalMinutes;
    private Map<String, Integer> minutesByTopic;
}
