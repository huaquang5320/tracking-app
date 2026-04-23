package com.thomas.trackingapp.domain.learning;

import com.thomas.trackingapp.domain.learning.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/learning-sessions")
@RequiredArgsConstructor
public class LearningSessionController {

    private final LearningSessionService learningSessionService;

    private static final Long TEMP_USER_ID = 1L;

    @PostMapping
    public ResponseEntity<LearningSessionResponse> createSession(
            @RequestBody @Valid LearningSessionRequest request) {
        LearningSessionResponse response = learningSessionService.createSession(TEMP_USER_ID, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<LearningSessionResponse>> getSessionsByRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        List<LearningSessionResponse> response =
                learningSessionService.getSessionsByRange(TEMP_USER_ID, from, to);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearningSessionResponse> updateSession(
            @PathVariable Long id,
            @RequestBody @Valid LearningSessionRequest request) {
        LearningSessionResponse response =
                learningSessionService.updateSession(TEMP_USER_ID, id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        learningSessionService.deleteSession(TEMP_USER_ID, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary")
    public ResponseEntity<LearningWeeklySummary> getWeeklySummary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        LearningWeeklySummary response =
                learningSessionService.getWeeklySummary(TEMP_USER_ID, from, to);
        return ResponseEntity.ok(response);
    }
}
