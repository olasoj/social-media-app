package com.olasoj.socialapp.comment.repository;

public class CommentSQLStatements {

    static String insertNewPost = """
                INSERT INTO comment (created_by, updated_by, content, like_count, post_id)
                VALUES (?, ?, ?, ?, ?);
            """;

    static String fetchPostByPostId = """
                        SELECT
                            comment_id,
                            content,
                            like_count,
                            
                            created_at,
                            updated_at ,
                            created_by,
                            updated_by,
                            
                            post_id,
                            version
                            FROM comment
                        WHERE post_id = ?
            """;

    static String fetchAllPosts = """
                        SELECT
                            comment_id,
                            content,
                            like_count,
                            
                            created_at,
                            updated_at ,
                            created_by,
                            updated_by,
                             COUNT(*) OVER() AS total_count,
                            post_id,
                            version
                            FROM comment P
                        WHERE content = (CASE WHEN ?::text IS NULL THEN P.content ELSE ? END)
                        AND post_id = ?
                        ORDER BY updated_at DESC
                        OFFSET (?) ROWS FETCH NEXT ? ROWS ONLY;
            """;

    static String updateCommentByComment_id = """
                            UPDATE comment
                            SET content = ?, updated_by =?
                            WHERE comment_id = ?;
            """;

    static String likePostByCommentId = """
                            
                                UPDATE comment
                                SET like_count = like_count + 1
                                , updated_by =?
                                WHERE comment_id = ?;
                            """;

    private CommentSQLStatements() {
    }
}