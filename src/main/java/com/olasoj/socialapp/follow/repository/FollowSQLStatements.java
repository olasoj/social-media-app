package com.olasoj.socialapp.follow.repository;

public class FollowSQLStatements {

    static String insertNewPost = """
                INSERT INTO follows (created_by, updated_by, follow_status, follow_social_media_account_id, social_media_account_id)
                VALUES (?, ?, ?::follow_status, ?, ?);
            """;

    static String fetchUniqueFollowRelationshipByFollowSID = """
                        SELECT
                            follow_id,
                            
                            created_at,
                            updated_at ,
                            created_by,
                            updated_by,
                            
                            follow_status,
                            social_media_account_id,
                            follow_social_media_account_id,
                            version
                            FROM follows
                        WHERE follow_social_media_account_id = ?
                        AND social_media_account_id = ?
            """;


    static String fetchFollowRelationshipByFollowSID = """
                        SELECT
                            follow_id,
                            
                            created_at,
                            updated_at ,
                            created_by,
                            updated_by,
                            
                            follow_status,
                            social_media_account_id,
                            follow_social_media_account_id,
                            version
                            FROM follows
                        WHERE follow_social_media_account_id = ?
            """;

    static String fetchFollowRelationshipByFollowingSID = """
                        SELECT
                            follow_id,
                            
                            created_at,
                            updated_at ,
                            created_by,
                            updated_by,
                            
                            follow_status,
                            social_media_account_id,
                            follow_social_media_account_id,
                            version
                            FROM follows
                        WHERE social_media_account_id = ?
            """;

    static String updateFollowRelationshipByFollowId = """
                            UPDATE follows
                            SET follow_status = ?, updated_by =?
                            WHERE follow_id = ?;
            """;


    private FollowSQLStatements() {
    }
}