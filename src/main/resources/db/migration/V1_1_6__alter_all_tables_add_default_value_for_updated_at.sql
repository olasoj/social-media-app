--Users
ALTER TABLE users ALTER COLUMN updated_at SET DEFAULT (NOW() AT TIME ZONE 'UTC');

-- social_media_account
ALTER TABLE social_media_account ALTER COLUMN updated_at SET DEFAULT (NOW() AT TIME ZONE 'UTC');

-- post
ALTER TABLE post ALTER COLUMN updated_at SET DEFAULT (NOW() AT TIME ZONE 'UTC');

-- comment
ALTER TABLE comment ALTER COLUMN updated_at SET DEFAULT (NOW() AT TIME ZONE 'UTC');

-- follows
ALTER TABLE follows ALTER COLUMN updated_at SET DEFAULT (NOW() AT TIME ZONE 'UTC');