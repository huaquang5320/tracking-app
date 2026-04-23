package com.thomas.trackingapp.domain.learning;

import com.thomas.trackingapp.common.exception.ResourceNotFoundException;
import com.thomas.trackingapp.domain.learning.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LearningSessionService {

    private final LearningSessionRepository learningSessionRepository;

    public LearningSessionResponse createSession(Long userId, LearningSessionRequest request) {
        LearningSession session = new LearningSession();
        session.setUserId(userId);
        session.setTopic(request.getTopic());
        session.setSource(request.getSource());
        session.setDurationMin(request.getDurationMin());
        session.setNote(request.getNote());
        session.setLoggedDate(request.getLoggedDate());

        LearningSession saved = learningSessionRepository.save(session);
        return LearningSessionResponse.from(saved);
    }

    public List<LearningSessionResponse> getSessionsByRange(Long userId, LocalDate from, LocalDate to) {
        return learningSessionRepository
                .findByUserIdAndLoggedDateBetweenOrderByLoggedDateDesc(userId, from, to)
                .stream()
                .map(LearningSessionResponse::from)
                .collect(Collectors.toList());
    }

    public LearningSessionResponse updateSession(Long userId, Long id, LearningSessionRequest request) {
        LearningSession session = learningSessionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Learning session not found"));

        session.setTopic(request.getTopic());
        session.setSource(request.getSource());
        session.setDurationMin(request.getDurationMin());
        session.setNote(request.getNote());

        LearningSession saved = learningSessionRepository.save(session);
        return LearningSessionResponse.from(saved);
    }

    public void deleteSession(Long userId, Long id) {
        LearningSession session = learningSessionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Learning session not found"));
        learningSessionRepository.delete(session);
    }

    public LearningWeeklySummary getWeeklySummary(Long userId, LocalDate from, LocalDate to) {
        List<LearningSession> sessions = learningSessionRepository
                .findByUserIdAndLoggedDateBetweenOrderByLoggedDateDesc(userId, from, to);

        int totalMinutes = sessions.stream()
                .mapToInt(LearningSession::getDurationMin)
                .sum();

        Map<String, Integer> minutesByTopic = sessions.stream()
                .collect(Collectors.groupingBy(
                        LearningSession::getTopic,
                        LinkedHashMap::new,
                        Collectors.summingInt(LearningSession::getDurationMin)
                ));

        return new LearningWeeklySummary(totalMinutes, minutesByTopic);
    }
}
