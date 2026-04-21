ALTER TABLE workout_sessions
    ADD COLUMN started_at TIMESTAMP,
    ADD COLUMN ended_at TIMESTAMP;

ALTER TABLE workout_sessions
    DROP COLUMN duration_min;