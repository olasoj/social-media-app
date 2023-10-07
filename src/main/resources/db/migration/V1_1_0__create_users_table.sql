CREATE TABLE IF NOT EXISTS users (
    user_id bigserial PRIMARY KEY,

    created_at timestamp(0) with time zone NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
    updated_at timestamp(0) with time zone NOT NULL,
    created_by text NOT NULL,
    updated_by text NOT NULL,

    username text NOT NULL,
    email text NOT NULL,
    profile_picture text NOT NULL,
    version integer NOT NULL DEFAULT 1
);


CREATE UNIQUE INDEX IF NOT EXISTS  idx_users_email
    ON users(email);

CREATE UNIQUE INDEX IF NOT EXISTS idx_users_username
    ON users(username);

-- username, email, profile picture,