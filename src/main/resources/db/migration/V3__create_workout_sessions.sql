CREATE TABLE workout_sessions (
    id            BIGSERIAL PRIMARY KEY,
    user_id       BIGINT NOT NULL,
    logged_date   DATE NOT NULL,
    duration_min  INTEGER,
    note          TEXT,
    created_at    TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_workout_sessions_user_date
ON workout_sessions (user_id, logged_date DESC);
