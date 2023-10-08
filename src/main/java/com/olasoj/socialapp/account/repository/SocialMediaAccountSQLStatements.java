package com.olasoj.socialapp.account.repository;

public class SocialMediaAccountSQLStatements {

    static String insertNewUser = """
                INSERT INTO social_media_account (created_by, updated_by, user_id)
                VALUES (?, ?, ?)
            """;

    static String fetchSocialMediaAccountByUserId = """
                        SELECT
                            social_media_account_id,

                            created_at,
                            updated_at ,
                            created_by,
                            updated_by,
                            
                            user_id,
                            version
                            FROM social_media_account
                        WHERE user_id = ?
            """;

    private SocialMediaAccountSQLStatements() {
    }
}