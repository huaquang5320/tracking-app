CREATE TABLE meal_logs (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    logged_date DATE NOT NULL,
    meal_type   VARCHAR(10) NOT NULL,
    food_name   VARCHAR(255) NOT NULL,
    calories    INTEGER,
    protein_g   DECIMAL(6,2),
    carb_g      DECIMAL(6,2),
    fat_g       DECIMAL(6,2),
    note        TEXT,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_meal_logs_user_date
ON meal_logs (user_id, logged_date DESC);
