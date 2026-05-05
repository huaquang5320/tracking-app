package com.thomas.trackingapp.domain.goal;

import com.thomas.trackingapp.common.exception.ResourceNotFoundException;
import com.thomas.trackingapp.domain.goal.dto.UserGoalRequest;
import com.thomas.trackingapp.domain.goal.dto.UserGoalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGoalService {

    private final UserGoalRepository userGoalRepository;

    public UserGoalResponse getGoal(Long userId) {
        return userGoalRepository.findByUserId(userId)
                .map(UserGoalResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("No goal set for user"));
    }

    public UserGoalResponse upsertGoal(Long userId, UserGoalRequest request) {
        UserGoal goal = userGoalRepository.findByUserId(userId)
                .orElseGet(() -> {
                    UserGoal newGoal = new UserGoal();
                    newGoal.setUserId(userId);
                    return newGoal;
                });

        goal.setTargetCalories(request.getTargetCalories());
        goal.setTargetProteinG(request.getTargetProteinG());
        goal.setTargetCarbG(request.getTargetCarbG());
        goal.setTargetFatG(request.getTargetFatG());

        UserGoal saved = userGoalRepository.save(goal);
        return UserGoalResponse.from(saved);
    }
}
