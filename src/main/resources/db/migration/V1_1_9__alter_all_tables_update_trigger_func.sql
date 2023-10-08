--Users
CREATE OR REPLACE FUNCTION user_update_trigger_function()
    RETURNS TRIGGER
AS
$$
BEGIN
    NEW.updated_at = NOW() AT TIME ZONE 'UTC';
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

-- social_media_account
CREATE OR REPLACE FUNCTION social_media_account_update_trigger_function()
    RETURNS TRIGGER
AS
$$
BEGIN
    NEW.updated_at = NOW() AT TIME ZONE 'UTC';
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

-- post
CREATE OR REPLACE FUNCTION post_update_trigger_function()
    RETURNS TRIGGER
AS
$$
BEGIN
    NEW.updated_at = NOW() AT TIME ZONE 'UTC';
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

-- comment
CREATE OR REPLACE FUNCTION comment_update_trigger_function()
    RETURNS TRIGGER
AS
$$
BEGIN
    NEW.updated_at = NOW() AT TIME ZONE 'UTC';
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

-- follows
CREATE OR REPLACE FUNCTION follows_update_trigger_function()
    RETURNS TRIGGER
AS
$$
BEGIN
    NEW.updated_at = NOW() AT TIME ZONE 'UTC';
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';