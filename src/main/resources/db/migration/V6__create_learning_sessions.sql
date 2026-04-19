CREATE TABLE learning_sessions (
    id            BIGSERIAL PRIMARY KEY,
    user_id       BIGINT NOT NULL,
    topic         VARCHAR(255) NOT NULL,
    source        VARCHAR(255),
    duration_min  INTEGER NOT NULL,
    note          TEXT,
    logged_date   DATE NOT NULL,
    created_at    TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_learning_sessions_user_date
ON learning_sessions (user_id, logged_date DESC);
