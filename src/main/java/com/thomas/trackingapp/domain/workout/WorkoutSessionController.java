package com.thomas.trackingapp.domain.workout;

import com.thomas.trackingapp.domain.workout.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/workout-sessions")
@RequiredArgsConstructor
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;

    private static final Long TEMP_USER_ID = 1L;

    @PostMapping
    public ResponseEntity<WorkoutSessionResponse> createSession(
            @RequestBody @Valid WorkoutSessionRequest request) {
        WorkoutSessionResponse response = workoutSessionService.createSession(TEMP_USER_ID, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutSessionResponse>> getSessionsByRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        List<WorkoutSessionResponse> response = workoutSessionService.getSessionsByRange(TEMP_USER_ID, from, to);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutSessionResponse> getSessionById(@PathVariable Long id) {
        WorkoutSessionResponse response = workoutSessionService.getSessionById(TEMP_USER_ID, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        workoutSessionService.deleteSession(TEMP_USER_ID, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/volume")
    public ResponseEntity<WorkoutVolumeResponse> getSessionVolume(@PathVariable Long id) {
        WorkoutVolumeResponse response = workoutSessionService.getSessionVolume(TEMP_USER_ID, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/exercises/history")
    public ResponseEntity<List<WorkoutExerciseHistoryResponse>> getExerciseHistory(
            @RequestParam String name,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        List<WorkoutExerciseHistoryResponse> response =
                workoutSessionService.getExerciseHistory(TEMP_USER_ID, name, from, to);
        return ResponseEntity.ok(response);
    }
}
