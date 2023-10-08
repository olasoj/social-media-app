ALTER TABLE follows
    ADD COLUMN IF NOT EXISTS social_media_account_id BIGINT;

ALTER TABLE follows
    ADD COLUMN IF NOT EXISTS follow_social_media_account_id BIGINT;

ALTER TABLE follows
    DROP CONSTRAINT follows_user_id_fkey,
    ADD FOREIGN KEY (social_media_account_id) REFERENCES social_media_account(social_media_account_id);

ALTER TABLE follows
    DROP CONSTRAINT follows_follow_user_id_fkey,
    ADD FOREIGN KEY (follow_social_media_account_id) REFERENCES social_media_account(social_media_account_id);