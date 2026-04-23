package com.thomas.trackingapp.domain.learning;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "learning_sessions")
@Getter
@Setter
@NoArgsConstructor
public class LearningSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "source")
    private String source;

    @Column(name = "duration_min", nullable = false)
    private Integer durationMin;

    @Column(name = "note")
    private String note;

    @Column(name = "logged_date", nullable = false)
    private LocalDate loggedDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
