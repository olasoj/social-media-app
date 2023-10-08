package com.olasoj.socialapp.util.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class DBTimeUtils {

    private DBTimeUtils() {
    }

    public static Instant getInstant(ResultSet rs, String columnName) throws SQLException {
        return rs.getTimestamp(columnName) == null ? null : rs.getTimestamp(columnName).toInstant();
    }
}
