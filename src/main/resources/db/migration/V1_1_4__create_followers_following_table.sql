CREATE TYPE follow_type AS ENUM ('FOLLOWER', 'FOLLOWING');

CREATE TABLE IF NOT EXISTS follows (
    follow_id bigserial PRIMARY KEY,

    created_at timestamp(0) with time zone NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
    updated_at timestamp(0) with time zone NOT NULL,
    created_by text NOT NULL,
    updated_by text NOT NULL,

    follow_type follow_type NOT NULL,

    user_id bigserial NOT NULL
        REFERENCES users (user_id)
            ON DELETE RESTRICT,

    follow_user_id bigserial NOT NULL
        REFERENCES users (user_id)
            ON DELETE RESTRICT,

    version integer NOT NULL DEFAULT 1
);

ALTER TABLE follows ADD CONSTRAINT followers_user_check CHECK (follow_type = 'FOLLOWER' AND user_id != follow_user_id  );
ALTER TABLE follows ADD CONSTRAINT followings_user_check CHECK (follow_type = 'FOLLOWING' AND user_id != follow_user_id  );
ALTER TABLE follows ADD CONSTRAINT follows_unique_check UNIQUE (follow_type, user_id, follow_user_id  );

-- username, email, profile picture,