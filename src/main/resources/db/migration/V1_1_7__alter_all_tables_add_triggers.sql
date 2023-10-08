--Users
CREATE OR REPLACE FUNCTION user_update_trigger_function()
    RETURNS TRIGGER
AS
$$
BEGIN
    UPDATE users SET updated_at = NOW() AT TIME ZONE 'UTC'
                 WHERE user_id = OLD.user_id;
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER user_update_trigger

    BEFORE UPDATE
    ON "users"
    FOR EACH ROW
    EXECUTE PROCEDURE user_update_trigger_function();



-- social_media_account
CREATE OR REPLACE FUNCTION social_media_account_update_trigger_function()
    RETURNS TRIGGER
AS
$$
BEGIN
    UPDATE social_media_account
    SET updated_at = NOW() AT TIME ZONE 'UTC'
    WHERE social_media_account_id = OLD.social_media_account_id;
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER social_media_account_update_trigger

    BEFORE UPDATE
    ON "social_media_account"
    FOR EACH ROW
EXECUTE PROCEDURE social_media_account_update_trigger_function();


-- post
CREATE OR REPLACE FUNCTION post_update_trigger_function()
    RETURNS TRIGGER
AS
$$
BEGIN
    UPDATE post
    SET updated_at = NOW() AT TIME ZONE 'UTC'
    WHERE post_id = OLD.post_id;
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER post_update_trigger

    BEFORE UPDATE
    ON "post"
    FOR EACH ROW
EXECUTE PROCEDURE post_update_trigger_function();



-- comment
CREATE OR REPLACE FUNCTION comment_update_trigger_function()
    RETURNS TRIGGER
AS
$$
BEGIN
    UPDATE comment
    SET updated_at = NOW() AT TIME ZONE 'UTC'
    WHERE comment_id = OLD.comment_id;
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER comment_update_trigger

    BEFORE UPDATE
    ON "comment"
    FOR EACH ROW
EXECUTE PROCEDURE comment_update_trigger_function();




-- follows
CREATE OR REPLACE FUNCTION follows_update_trigger_function()
    RETURNS TRIGGER
AS
$$
BEGIN
    UPDATE follows
    SET updated_at = NOW() AT TIME ZONE 'UTC'
    WHERE follow_id = OLD.follow_id;
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER follows_update_trigger

    BEFORE UPDATE
    ON "follows"
    FOR EACH ROW
EXECUTE PROCEDURE follows_update_trigger_function();



-- username, email, profile picture,