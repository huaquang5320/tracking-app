package com.thomas.trackingapp.domain.goal;

import com.thomas.trackingapp.domain.goal.dto.UserGoalRequest;
import com.thomas.trackingapp.domain.goal.dto.UserGoalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-goals")
@RequiredArgsConstructor
public class UserGoalController {

    private final UserGoalService userGoalService;

    // TODO: replace hardcoded userId with @AuthenticationPrincipal after Auth is implemented
    private static final Long TEMP_USER_ID = 1L;

    @GetMapping
    public ResponseEntity<UserGoalResponse> getGoal() {
        UserGoalResponse response = userGoalService.getGoal(TEMP_USER_ID);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<UserGoalResponse> upsertGoal(
            @RequestBody @Valid UserGoalRequest request
    ) {
        UserGoalResponse response = userGoalService.upsertGoal(TEMP_USER_ID, request);
        return ResponseEntity.ok(response);
    }
}
