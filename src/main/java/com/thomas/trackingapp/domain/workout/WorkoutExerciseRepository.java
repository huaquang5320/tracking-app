package com.thomas.trackingapp.domain.workout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

    List<WorkoutExercise> findByNameAndSession_UserIdAndSession_StartedAtBetweenOrderBySession_StartedAtAsc(
            String name,
            Long userId,
            LocalDateTime from,
            LocalDateTime to
    );
}
