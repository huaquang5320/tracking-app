CREATE INDEX idx_workout_sessions_user_started
ON workout_sessions (user_id, started_at DESC);