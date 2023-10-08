package com.olasoj.socialapp.post.repository;

public class PostSQLStatements {

    static String insertNewPost = """
                INSERT INTO post (created_by, updated_by, content, like_count, social_media_account_id)
                VALUES (?, ?, ?, ?, ?)
            """;

    static String fetchPostByPostId = """
                        SELECT
                            post_id,
                            content,
                            like_count,
                            
                            created_at,
                            updated_at ,
                            created_by,
                            updated_by,
                            
                            social_media_account_id,
                            version
                            FROM post
                        WHERE post_id = ?
            """;

    static String fetchAllPosts = """
                        SELECT
                            post_id,
                            content,
                            like_count,
                            
                            created_at,
                            updated_at ,
                            created_by,
                            updated_by,
                             COUNT(*) OVER() AS total_count,
                            social_media_account_id,
                            version
                            FROM post P
                        WHERE content = (CASE WHEN ?::text IS NULL THEN P.content ELSE ? END)
                        ORDER BY updated_at DESC
            """;

//                            OFFSET (?) ROWS FETCH NEXT ? ROWS ONLY;
//    to_tsvector('simple', content) @@ plainto_tsquery('simple', ?)
    static String updatePostByPostId = """
                            UPDATE post
                            SET content = ?, updated_by =?
                            WHERE post_id = ?
            """;

    private PostSQLStatements() {
    }
}