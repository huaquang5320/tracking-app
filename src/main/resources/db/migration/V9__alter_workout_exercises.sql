ALTER TABLE workout_exercises
    ADD COLUMN rpe DECIMAL(3,1),
    ADD COLUMN rest_seconds INTEGER,
    ADD COLUMN note TEXT;