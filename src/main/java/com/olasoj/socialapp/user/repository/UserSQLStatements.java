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