

ALTER TABLE comment
    ADD COLUMN social_media_account_id BIGINT NOT NULL
        REFERENCES social_media_account (social_media_account_id)
            ON DELETE RESTRICT DEFAULT 2;