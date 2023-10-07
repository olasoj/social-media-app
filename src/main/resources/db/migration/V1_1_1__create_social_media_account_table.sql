CREATE TABLE IF NOT EXISTS social_media_account (
    social_media_account_id bigserial PRIMARY KEY,

    created_at timestamp(0) with time zone NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
    updated_at timestamp(0) with time zone NOT NULL,
    created_by text NOT NULL,
    updated_by text NOT NULL,

    user_id bigint NOT NULL
        REFERENCES users (user_id)
            ON DELETE RESTRICT,
    version integer NOT NULL DEFAULT 1
);

CREATE UNIQUE INDEX IF NOT EXISTS  idx_social_media_account_user_id
    ON social_media_account(user_id);

-- username, email, profile picture,