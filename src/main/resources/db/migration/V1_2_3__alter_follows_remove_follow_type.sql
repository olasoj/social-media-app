
ALTER TABLE follows
    DROP CONSTRAINT followers_user_check;

ALTER TABLE follows
    DROP CONSTRAINT followings_user_check;

ALTER TABLE follows DROP COLUMN follow_type;