CREATE INDEX IF NOT EXISTS post_content_idx ON post USING GIN (to_tsvector('simple', content));
CREATE INDEX IF NOT EXISTS comment_content_idx ON comment USING GIN (to_tsvector('simple', content));