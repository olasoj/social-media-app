package com.olasoj.socialapp.user.transformer;

import com.olasoj.socialapp.post.model.PagingInfo;
import com.olasoj.socialapp.user.acl.role.Role;
import com.olasoj.socialapp.user.model.BlogUser;
import com.olasoj.socialapp.user.model.User;
import com.olasoj.socialapp.user.model.UserAndAccountInfo;
import com.olasoj.socialapp.user.model.UserWithPageInfoResult;
import com.olasoj.socialapp.util.db.DBTimeUtils;
import io.jsonwebtoken.lang.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserAssembler {

    private UserAssembler() {

    }


    public static UserWithPageInfoResult assemble(List<Map<String, Object>> mapList, PagingInfo pagingInfo) {
        Assert.notNull(mapList, "MapList cannot be empty");
        Assert.notNull(pagingInfo, "PagingInfo cannot be empty");

        if (mapList.isEmpty()) return new UserWithPageInfoResult(Collections.emptyList(), pagingInfo);

        Map<String, Object> map = mapList.get(0);
        int totalCount = Math.toIntExact((Long) map.get("total_count"));

        List<UserAndAccountInfo> postList = mapList
                .stream().map(UserAssembler::assembleUAAI)
                .toList();

        return new UserWithPageInfoResult(postList, new PagingInfo(pagingInfo.getPageSize(), pagingInfo.getCurrentPage(), totalCount));
    }


    public static UserAndAccountInfo assembleUAAI(Map<String, Object> map) {
        User user = UserAssembler.assemble(map);
        return new UserAndAccountInfo(user, (Long) map.get("social_media_account_id"));
    }

    public static BlogUser assemble(Map<String, Object> mapItem) {

        Assert.notNull(mapItem, "MapItem cannot be null");

        return BlogUser.builder()
                .userId((Long) mapItem.get("user_id"))
                .username((String) mapItem.get("username"))
                .email((String) mapItem.get("email"))
                .password((String) mapItem.get("password"))

//                        .createdAt(DBTimeUtils.getInstant(mapItem, "created_at"))
//                        .updatedAt(DBTimeUtils.getInstant(mapItem, ("updated_at")))
                .createdBy((String) mapItem.get("created_by"))
                .updatedBy((String) mapItem.get("updated_by"))

                .profilePhoto((String) mapItem.get("profile_picture"))
                .version((Integer) mapItem.get("version"))

                .accessControlList(List.of(Role.WRITE, Role.READ))
                .build();

    }

    public static BlogUser assemble(ResultSet rs) throws SQLException {
        return BlogUser.builder()
                .userId(rs.getLong("user_id"))
                .username(rs.getString("username"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))

                .createdAt(DBTimeUtils.getInstant(rs, "created_at"))
                .updatedAt(DBTimeUtils.getInstant(rs, ("updated_at")))
                .createdBy(rs.getString("created_by"))
                .updatedBy(rs.getString("updated_by"))

                .profilePhoto(rs.getString("profile_picture"))
                .version(rs.getInt("version"))

                .accessControlList(List.of(Role.WRITE, Role.READ))
                .build();
    }
}
