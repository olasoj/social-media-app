package com.olasoj.socialapp.comment.repository;

import com.olasoj.socialapp.audit.AuditUtils;
import com.olasoj.socialapp.comment.model.Comment;
import com.olasoj.socialapp.comment.model.CommentWithPageInfoResult;
import com.olasoj.socialapp.comment.model.ReadCommentsRequest;
import com.olasoj.socialapp.comment.transformer.CommentAssembler;
import com.olasoj.socialapp.post.model.PagingInfo;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.olasoj.socialapp.comment.repository.CommentSQLStatements.*;

@Repository
public class DefaultCommentRepository implements CommentRepository {

    private final JdbcTemplate jdbcOperations;

    public DefaultCommentRepository(@Qualifier("jdbcTemplate") JdbcTemplate jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public boolean saveComment(Comment comment) {

        AuditUtils.onCreate(comment);

        int update = jdbcOperations.update(
                insertNewPost,
                comment.getCreatedBy(),
                comment.getUpdatedBy(),
                comment.getContent(),
                comment.getLikeCount(),
                comment.getPostId(),
                comment.getSocialMediaAccountId()
        );
        return update == 1;
    }

    @Override
    public Optional<Comment> findCommentByCommentId(Long postId) {
        return Optional.ofNullable(
                jdbcOperations.query(
                        fetchPostByPostId
                        , rs -> {
                            return CommentRowMapper.socialMediaAccountRowMapper.mapRow(rs, rs.getRow());
                        }
                        , postId
                )
        );
    }

    @Override
    public CommentWithPageInfoResult findAllCommentsByPostId(Long postId, ReadCommentsRequest readCommentsRequest) {

        Assert.notNull(readCommentsRequest, "ReadCommentsRequest cannot be null");

        PagingInfo pagingInfo = readCommentsRequest.getPagingInfo();
        Assert.notNull(pagingInfo, "Paging cannot be null");

        List<Map<String, Object>> comments = jdbcOperations.queryForList(
                fetchAllPosts
                , StringUtils.isBlank(readCommentsRequest.getContent()) ? null : readCommentsRequest.getContent()
                , StringUtils.isBlank(readCommentsRequest.getContent()) ? null : readCommentsRequest.getContent()
                , postId
                , pagingInfo.getPageSize() * (pagingInfo.getCurrentPage() - 1)
                , pagingInfo.getPageSize()
        );

        return CommentAssembler.assemble(comments, pagingInfo);
    }

    @Override
    public boolean deleteComment(Long postId) {
        return false;
    }

    @Override
    public boolean updateCommentContent(Comment comment) {

        Assert.notNull(comment, "Post cannot be null");
        Assert.notNull(comment.getPostId(), "PostId cannot be null");
        AuditUtils.onUpdate(comment);

        int update = jdbcOperations.update(
                updateCommentByCommentId,
                comment.getContent(),
                comment.getUpdatedBy(),
                comment.getPostId()
        );
        return update == 1;
    }

    @Override
    public boolean likeCommentContent(Comment comment) {

        Assert.notNull(comment, "Post cannot be null");
        Assert.notNull(comment.getPostId(), "PostId cannot be null");
        AuditUtils.onUpdate(comment);

        int update = jdbcOperations.update(
                likePostByCommentId,
                comment.getUpdatedBy(),
                comment.getPostId()
        );
        return update == 1;
    }

    static final class CommentRowMapper implements RowMapper<Comment> {

        static final RowMapper<Comment> socialMediaAccountRowMapper = new CommentRowMapper();


        public Comment mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            return CommentAssembler.assemble(rs);
        }
    }
}
