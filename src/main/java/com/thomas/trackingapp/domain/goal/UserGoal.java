package com.thomas.trackingapp.domain.goal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_goals")
@Getter
@Setter
@NoArgsConstructor
public class UserGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "target_calories", nullable = false)
    private Integer targetCalories;

    @Column(name = "target_protein_g", nullable = false)
    private BigDecimal targetProteinG;

    @Column(name = "target_carb_g", nullable = false)
    private BigDecimal targetCarbG;

    @Column(name = "target_fat_g", nullable = false)
    private BigDecimal targetFatG;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
