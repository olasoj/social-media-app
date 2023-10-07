CREATE TABLE IF NOT EXISTS comment (
    comment_id bigserial PRIMARY KEY,

    created_at timestamp(0) with time zone NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
    updated_at timestamp(0) with time zone NOT NULL,
    created_by text NOT NULL,
    updated_by text NOT NULL,

     content text NOT NULL,
     like_count integer NOT NULL,

     post_id bigserial NOT NULL
        REFERENCES post (post_id)
            ON DELETE RESTRICT,
    version integer NOT NULL DEFAULT 1
);

CREATE INDEX IF NOT EXISTS  idx_comment_post_id
    ON comment(post_id);

ALTER TABLE comment ADD CONSTRAINT comment_likes_check CHECK (like_count >= 0);

-- username, email, profile picture,