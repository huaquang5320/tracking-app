@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "workout_sessions")
public class WorkoutSession {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1 session có nhiều exercises
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutExercise> exercises = new ArrayList<>();
 
    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String note;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;
    
}