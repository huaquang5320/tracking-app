package com.thomas.trackingapp.domain.workout;

import com.thomas.trackingapp.common.exception.ResourceNotFoundException;
import com.thomas.trackingapp.domain.workout.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutSessionService {

    private final WorkoutSessionRepository workoutSessionRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    @Transactional
    public WorkoutSessionResponse createSession(Long userId, WorkoutSessionRequest request) {
        WorkoutSession session = new WorkoutSession();
        session.setUserId(userId);
        session.setStartedAt(request.getStartedAt());
        session.setEndedAt(request.getEndedAt());
        session.setNote(request.getNote());

        List<WorkoutExercise> exercises = request.getExercises().stream().map(req -> {
            WorkoutExercise exercise = new WorkoutExercise();
            exercise.setSession(session);
            exercise.setName(req.getName());
            exercise.setSets(req.getSets());
            exercise.setReps(req.getReps());
            exercise.setWeightKg(req.getWeightKg());
            exercise.setRpe(req.getRpe());
            exercise.setRestSeconds(req.getRestSeconds());
            exercise.setNote(req.getNote());
            return exercise;
        }).collect(Collectors.toList());

        session.setExercises(exercises);
        WorkoutSession saved = workoutSessionRepository.save(session);
        return WorkoutSessionResponse.from(saved);
    }

    public List<WorkoutSessionResponse> getSessionsByRange(Long userId, LocalDateTime from, LocalDateTime to) {
        return workoutSessionRepository
                .findByUserIdAndStartedAtBetweenOrderByStartedAtDesc(userId, from, to)
                .stream()
                .map(WorkoutSessionResponse::from)
                .collect(Collectors.toList());
    }

    public WorkoutSessionResponse getSessionById(Long userId, Long id) {
        WorkoutSession session = workoutSessionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout session not found"));
        return WorkoutSessionResponse.from(session);
    }

    @Transactional
    public void deleteSession(Long userId, Long id) {
        WorkoutSession session = workoutSessionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout session not found"));
        workoutSessionRepository.delete(session);
    }

    public WorkoutVolumeResponse getSessionVolume(Long userId, Long id) {
        WorkoutSession session = workoutSessionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout session not found"));

        BigDecimal totalVolume = session.getExercises().stream()
                .filter(e -> e.getSets() != null && e.getReps() != null && e.getWeightKg() != null)
                .map(e -> e.getWeightKg()
                        .multiply(BigDecimal.valueOf(e.getSets()))
                        .multiply(BigDecimal.valueOf(e.getReps())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new WorkoutVolumeResponse(id, totalVolume);
    }

    public List<WorkoutExerciseHistoryResponse> getExerciseHistory(
            Long userId, String name, LocalDateTime from, LocalDateTime to) {
        return workoutExerciseRepository
                .findByNameAndSession_UserIdAndSession_StartedAtBetweenOrderBySession_StartedAtAsc(
                        name, userId, from, to)
                .stream()
                .map(WorkoutExerciseHistoryResponse::from)
                .collect(Collectors.toList());
    }
}
