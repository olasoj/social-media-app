

CREATE TYPE follow_status AS ENUM ('ACCEPTED', 'PENDING', 'REJECTED', 'CANCEL');

ALTER TABLE follows
    ADD COLUMN follow_status follow_status NOT NULL;

ALTER TABLE follows
    ADD CONSTRAINT follows_follow_status_unique_check UNIQUE (user_id, follow_user_id  );
