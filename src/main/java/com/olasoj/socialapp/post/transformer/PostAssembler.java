package com.olasoj.socialapp.post.transformer;

import com.olasoj.socialapp.post.model.PagingInfo;
import com.olasoj.socialapp.post.model.Post;
import com.olasoj.socialapp.post.model.PostWithPageInfoResult;
import com.olasoj.socialapp.util.db.DBTimeUtils;
import io.jsonwebtoken.lang.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PostAssembler {


    private PostAssembler() {

    }


    public static PostWithPageInfoResult assemble(List<Map<String, Object>> mapList, PagingInfo pagingInfo) {
        Assert.notNull(mapList, "MapList cannot be empty");
        Assert.notNull(pagingInfo, "PagingInfo cannot be empty");

        if (mapList.isEmpty()) return new PostWithPageInfoResult(Collections.emptyList(), pagingInfo);

        Map<String, Object> map = mapList.get(0);
        int totalCount = Math.toIntExact((Long) map.get("total_count"));

        List<Post> postList = mapList
                .stream().map(PostAssembler::assemble)
                .toList();

        return new PostWithPageInfoResult(postList, new PagingInfo(pagingInfo.getPageSize(), pagingInfo.getCurrentPage(), totalCount));
    }

    public static Post assemble(Map<String, Object> mapItem) {

        Assert.notNull(mapItem, "MapItem cannot be null");

        return Post.builder()
                .postId((Long) mapItem.get("post_id"))

                .likeCount((Integer) mapItem.get("like_count"))
                .content((String) mapItem.get("content"))

                //TODO: Fix this
//                    .createdAt(DBTimeUtils.getInstant(mapItem, "created_at"))
//                    .updatedAt(DBTimeUtils.getInstant(mapItem, ("updated_at")))
                .createdBy((String) mapItem.get("created_by"))
                .updatedBy((String) mapItem.get("updated_by"))

                .socialMediaAccountId((Long) mapItem.get("social_media_account_id"))
                .version((Integer) mapItem.get("version"))

                .build();

    }

    public static Post assemble(ResultSet rs) throws SQLException {

        if (rs.next()) {

            return Post.builder()
                    .postId(rs.getLong("post_id"))

                    .likeCount(rs.getInt("like_count"))
                    .content(rs.getString("content"))

                    .createdAt(DBTimeUtils.getInstant(rs, "created_at"))
                    .updatedAt(DBTimeUtils.getInstant(rs, ("updated_at")))
                    .createdBy(rs.getString("created_by"))
                    .updatedBy(rs.getString("updated_by"))

                    .socialMediaAccountId(rs.getLong("social_media_account_id"))
                    .version(rs.getInt("version"))

                    .build();
        }

        return null;
    }
}
