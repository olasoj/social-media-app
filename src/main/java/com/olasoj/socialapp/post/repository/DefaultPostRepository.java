package com.olasoj.socialapp.post.repository;

import com.olasoj.socialapp.audit.AuditUtils;
import com.olasoj.socialapp.post.model.PagingInfo;
import com.olasoj.socialapp.post.model.Post;
import com.olasoj.socialapp.post.model.PostWithPageInfoResult;
import com.olasoj.socialapp.post.model.request.ReadPostsRequest;
import com.olasoj.socialapp.post.transformer.PostAssembler;
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

import static com.olasoj.socialapp.post.repository.PostSQLStatements.*;

@Repository
public class DefaultPostRepository implements PostRepository {

    private final JdbcTemplate jdbcOperations;

    public DefaultPostRepository(@Qualifier("jdbcTemplate") JdbcTemplate jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Post saveBlogPost(Post post) {

        AuditUtils.onCreate(post);

        int update = jdbcOperations.update(
                insertNewPost,
                post.getCreatedBy(),
                post.getUpdatedBy(),
                post.getContent(),
                post.getLikeCount(),
                post.getSocialMediaAccountId()
        );
        return null;
    }

    @Override
    public Optional<Post> findPostByPostId(Long postId) {
        return Optional.ofNullable(
                jdbcOperations.query(
                        fetchPostByPostId
                        , rs -> {
                            return PostRowMapper.socialMediaAccountRowMapper.mapRow(rs, rs.getRow());
                        }
                        , postId
                )
        );
    }

    @Override
    public PostWithPageInfoResult findAllPost(ReadPostsRequest readPostsRequest) {

        Assert.notNull(readPostsRequest, "ReadPostsRequest cannot be null");
        PagingInfo pagingInfo = readPostsRequest.getPagingInfo();
        Assert.notNull(pagingInfo, "Paging cannot be null");

        List<Map<String, Object>> posts = jdbcOperations.queryForList(
                fetchAllPosts
                , StringUtils.isBlank(readPostsRequest.getContent()) ? null : readPostsRequest.getContent()
                , StringUtils.isBlank(readPostsRequest.getContent()) ? null : readPostsRequest.getContent()
//                , pagingInfo.getPageSize() * pagingInfo.getCurrentPage() - 1
//                , pagingInfo.getPageSize()
        );

        return PostAssembler.assemble(posts, pagingInfo);
    }

    @Override
    public Optional<Post> deletePost(Long postId) {
        return Optional.empty();
    }

    @Override
    public Post updatePostContent(Post post) {

        Assert.notNull(post, "Post cannot be null");
        Assert.notNull(post.getPostId(), "PostId cannot be null");
        AuditUtils.onUpdate(post);

        int update = jdbcOperations.update(
                updatePostByPostId,
                post.getContent(),
                post.getUpdatedBy(),
                post.getPostId()
        );
        return null;
    }

    static final class PostRowMapper implements RowMapper<Post> {

        static final RowMapper<Post> socialMediaAccountRowMapper = new PostRowMapper();


        public Post mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            return PostAssembler.assemble(rs);
        }
    }
}
