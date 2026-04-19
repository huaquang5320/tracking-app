package com.thomas.trackingapp.domain.weight;

import com.thomas.trackingapp.common.exception.DuplicateResourceException;
import com.thomas.trackingapp.common.exception.ResourceNotFoundException;
import com.thomas.trackingapp.domain.weight.dto.WeightLogRequest;
import com.thomas.trackingapp.domain.weight.dto.WeightLogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeightLogService {

    private final WeightLogRepository weightLogRepository;

    public WeightLogResponse logWeight(Long userId, WeightLogRequest request) {
        weightLogRepository.findByUserIdAndLoggedDate(userId, request.getLoggedDate())
                .ifPresent(w -> {
                    throw new DuplicateResourceException(
                            "Already logged weight for " + request.getLoggedDate()
                    );
                });

        WeightLog weightLog = new WeightLog();
        weightLog.setUserId(userId);
        weightLog.setWeightKg(request.getWeightKg());
        weightLog.setLoggedDate(request.getLoggedDate());
        weightLog.setNote(request.getNote());

        WeightLog saved = weightLogRepository.save(weightLog);
        return WeightLogResponse.from(saved);
    }

    public List<WeightLogResponse> getWeightTrend(Long userId, LocalDate from, LocalDate to) {
        return weightLogRepository
                .findByUserIdAndLoggedDateBetweenOrderByLoggedDateDesc(userId, from, to)
                .stream()
                .map(WeightLogResponse::from)
                .collect(Collectors.toList());
    }

    public WeightLogResponse getTodayLog(Long userId) {
        return weightLogRepository.findByUserIdAndLoggedDate(userId, LocalDate.now())
                .map(WeightLogResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("No weight log found for today"));
    }
}
