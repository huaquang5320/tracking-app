CREATE TABLE weight_logs (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    weight_kg   DECIMAL(5,2) NOT NULL,
    logged_date DATE NOT NULL,
    note        TEXT,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE weight_logs
ADD CONSTRAINT uq_weight_logs_user_date
UNIQUE (user_id, logged_date);
