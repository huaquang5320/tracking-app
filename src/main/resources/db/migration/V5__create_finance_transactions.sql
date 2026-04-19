CREATE TABLE finance_transactions (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    type        VARCHAR(10) NOT NULL,
    amount      DECIMAL(15,2) NOT NULL,
    category    VARCHAR(100) NOT NULL,
    description TEXT,
    logged_date DATE NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_finance_transactions_user_date
ON finance_transactions (user_id, logged_date DESC);
