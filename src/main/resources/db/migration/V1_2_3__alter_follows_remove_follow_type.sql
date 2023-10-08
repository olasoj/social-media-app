
ALTER TABLE follows ADD CONSTRAINT followers_user_check CHECK (social_media_account_id != follow_social_media_account_id  );
ALTER TABLE follows ADD CONSTRAINT follows_follow_status_unique_check UNIQUE (social_media_account_id, follow_social_media_account_id  );

ALTER TABLE follows DROP COLUMN follow_type;