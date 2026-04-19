CREATE TABLE workout_exercises (
    id          BIGSERIAL PRIMARY KEY,
    session_id  BIGINT NOT NULL REFERENCES workout_sessions(id),
    name        VARCHAR(255) NOT NULL,
    sets        INTEGER,
    reps        INTEGER,
    weight_kg   DECIMAL(5,2),
    created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_workout_exercises_session
ON workout_exercises (session_id);
