package com.thomas.trackingapp.domain.weight;

import com.thomas.trackingapp.domain.weight.dto.WeightLogRequest;
import com.thomas.trackingapp.domain.weight.dto.WeightLogResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/weight-logs")
@RequiredArgsConstructor
public class WeightLogController {

    private final WeightLogService weightLogService;

    // TODO: replace hardcoded userId with @AuthenticationPrincipal after Auth is implemented
    private static final Long TEMP_USER_ID = 1L;

    @PostMapping
    public ResponseEntity<WeightLogResponse> logWeight(
            @RequestBody @Valid WeightLogRequest request
    ) {
        WeightLogResponse response = weightLogService.logWeight(TEMP_USER_ID, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/today")
    public ResponseEntity<WeightLogResponse> getTodayLog() {
        WeightLogResponse response = weightLogService.getTodayLog(TEMP_USER_ID);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/trend")
    public ResponseEntity<List<WeightLogResponse>> getWeightTrend(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        List<WeightLogResponse> response = weightLogService.getWeightTrend(TEMP_USER_ID, from, to);
        return ResponseEntity.ok(response);
    }
}
