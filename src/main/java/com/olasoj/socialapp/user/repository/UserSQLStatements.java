package com.olasoj.socialapp.user.repository;

public class UserSQLStatements {

    static String insertNewUser = """
                INSERT INTO users (created_by, updated_by, username, email, password, profile_picture)
                VALUES (?, ?, ?, ?, ?, ?)
            """;

    static String fetchNewUserByUsername = """
                        SELECT
                            user_id,
                            username,
                            email,
                            password,
                            
                            created_at,
                            updated_at ,
                            created_by,
                            updated_by,
                            
                            profile_picture,
                            version
                            FROM users
                        WHERE username = ?
            """;


    static String fetchUserAndAccountIdByUsername = """
                        SELECT
                            U.user_id,
                            U.username,
                            U.email,
                            U.password,
                            
                            U.created_at,
                            U.updated_at ,
                            U.created_by,
                            U.updated_by,
                            
                            U.profile_picture,
                            U.version,
                            SMA.social_media_account_id
                            FROM users U
                            INNER JOIN social_media_account SMA ON SMA.user_id = U.user_id
                        WHERE username = ?;
            """;

    static String fetchNewUserByEmail = """
                        SELECT
                            user_id,
                            username,
                            email,
                            password,
                            
                            created_at,
                            updated_at ,
                            created_by,
                            updated_by,
                            
                            profile_picture,
                            version
                         FROM users
                        WHERE email = ?
            """;

    private UserSQLStatements() {
    }
}