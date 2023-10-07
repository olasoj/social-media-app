CREATE TABLE IF NOT EXISTS post (
     post_id bigserial PRIMARY KEY,

    created_at timestamp(0) with time zone NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
    updated_at timestamp(0) with time zone NOT NULL,
    created_by text NOT NULL,
    updated_by text NOT NULL,

     content text NOT NULL,
     like_count integer NOT NULL,

     social_media_account_id bigserial NOT NULL
        REFERENCES social_media_account (social_media_account_id)
            ON DELETE RESTRICT,

    version integer NOT NULL DEFAULT 1
);

CREATE INDEX IF NOT EXISTS  idx_post_social_media_account_id
    ON post(social_media_account_id);

ALTER TABLE post ADD CONSTRAINT post_likes_check CHECK (like_count >= 0);
-- username, email, profile picture,