package com.thomas.trackingapp.domain.workout;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "workout_exercises")
@Getter
@Setter
@NoArgsConstructor
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private WorkoutSession session;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sets")
    private Integer sets;

    @Column(name = "reps")
    private Integer reps;

    @Column(name = "weight_kg")
    private BigDecimal weightKg;

    @Column(name = "rpe")
    private BigDecimal rpe;

    @Column(name = "rest_seconds")
    private Integer restSeconds;

    @Column(name = "note")
    private String note;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
