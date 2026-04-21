@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "workout_exercises")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nhiều exercises thuộc 1 session
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private WorkoutSession session;

    @Column(name = "name", nullable = false)
    private String name;

    private Integer sets;

    private Integer reps;

    @Column(name = "weight_kg")
    private BigDecimal weightKg;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    @Column(name = "rpe")
    private BigDecimal rpe;
    @Column(name = "rest_seconds")
    private Integer restSeconds;
    @Column(name = "note")
    private String note;
}