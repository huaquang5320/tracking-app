CREATE TABLE user_goals (
    id               BIGSERIAL PRIMARY KEY,
    user_id          BIGINT NOT NULL,
    target_calories  INTEGER NOT NULL,
    target_protein_g DECIMAL(5,1) NOT NULL,
    target_carb_g    DECIMAL(5,1) NOT NULL,
    target_fat_g     DECIMAL(5,1) NOT NULL,
    created_at       TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE user_goals
ADD CONSTRAINT uq_user_goals_user
UNIQUE (user_id);
