package com.olasoj.socialapp.comment.transformer;

import com.olasoj.socialapp.comment.model.Comment;
import com.olasoj.socialapp.comment.model.CommentWithPageInfoResult;
import com.olasoj.socialapp.post.model.PagingInfo;
import com.olasoj.socialapp.util.db.DBTimeUtils;
import io.jsonwebtoken.lang.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CommentAssembler {


    private CommentAssembler() {

    }


    public static CommentWithPageInfoResult assemble(List<Map<String, Object>> mapList, PagingInfo pagingInfo) {
        Assert.notNull(mapList, "MapList cannot be empty");
        Assert.notNull(pagingInfo, "PagingInfo cannot be empty");

        if (mapList.isEmpty()) return new CommentWithPageInfoResult(Collections.emptyList(), pagingInfo);

        Map<String, Object> map = mapList.get(0);
        int totalCount = Math.toIntExact((Long) map.get("total_count"));

        List<Comment> postList = mapList
                .stream().map(CommentAssembler::assemble)
                .toList();

        return new CommentWithPageInfoResult(postList, new PagingInfo(pagingInfo.getPageSize(), pagingInfo.getCurrentPage(), totalCount));
    }

    public static Comment assemble(Map<String, Object> mapItem) {

        Assert.notNull(mapItem, "MapItem cannot be null");

        return Comment.builder()
                .commentId((Long) mapItem.get("comment_id"))


                .likeCount((Integer) mapItem.get("like_count"))
                .content((String) mapItem.get("content"))

                //TODO: Fix this
//                    .createdAt(DBTimeUtils.getInstant(mapItem, "created_at"))
//                    .updatedAt(DBTimeUtils.getInstant(mapItem, ("updated_at")))
                .createdBy((String) mapItem.get("created_by"))
                .updatedBy((String) mapItem.get("updated_by"))

                .postId((Long) mapItem.get("post_id"))
                .socialMediaAccountId((Long) mapItem.get("social_media_account_id"))
                .version((Integer) mapItem.get("version"))

                .build();

    }

    public static Comment assemble(ResultSet rs) throws SQLException {

        if (rs.next()) {

            return Comment.builder()
                    .commentId(rs.getLong("comment_id"))

                    .likeCount(rs.getInt("like_count"))
                    .content(rs.getString("content"))

                    .createdAt(DBTimeUtils.getInstant(rs, "created_at"))
                    .updatedAt(DBTimeUtils.getInstant(rs, ("updated_at")))
                    .createdBy(rs.getString("created_by"))
                    .updatedBy(rs.getString("updated_by"))

                    .postId(rs.getLong("post_id"))
                    .socialMediaAccountId(rs.getLong("social_media_account_id"))
                    .version(rs.getInt("version"))

                    .build();
        }

        return null;
    }
}
