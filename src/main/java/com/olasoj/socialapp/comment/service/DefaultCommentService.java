package com.olasoj.socialapp.comment.service;

import com.olasoj.socialapp.comment.model.*;
import com.olasoj.socialapp.comment.model.request.CreateCommentRequest;
import com.olasoj.socialapp.comment.model.request.EditCommentRequest;
import com.olasoj.socialapp.comment.repository.CommentRepository;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DefaultCommentService implements CommentService {
    private static final String BLOG_USER_PRINCIPAL_CANNOT_BE_NULL = "BlogUserPrincipal cannot be null";
    private final CommentRepository commentRepository;

    public DefaultCommentService(@Qualifier("defaultCommentRepository") CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public GenericCommentResult createComment(Long postId, CreateCommentRequest createCommentRequest, BlogUserPrincipal blogUserPrincipal) {
        Assert.notNull(createCommentRequest, "CreateBlogPostRequest cannot be null");
        Assert.notNull(blogUserPrincipal, BLOG_USER_PRINCIPAL_CANNOT_BE_NULL);

        Comment post = Comment.builder()
                .content(createCommentRequest.content())
                .likeCount(0)
                .postId(postId)
                .socialMediaAccountId(blogUserPrincipal.accountId())
                .build();

        boolean saveBlogPost = commentRepository.saveComment(post);
        return getGenericCommentResult(saveBlogPost);

    }

    private GenericCommentResult getGenericCommentResult(boolean saveBlogPost) {
        return new GenericCommentResult(saveBlogPost ? "Operation successful" : "Operation failed");
    }

    @Override
    @Transactional
    public GenericCommentResult editComment(Long commentId, EditCommentRequest editCommentRequest, BlogUserPrincipal blogUserPrincipal) {

        Assert.notNull(commentId, "CommentId cannot be null");
        Assert.notNull(editCommentRequest, "EditBlogPostRequest cannot be null");
        Assert.notNull(blogUserPrincipal, BLOG_USER_PRINCIPAL_CANNOT_BE_NULL);

        Comment post = getCommentInternal(commentId);

        post.setContent(editCommentRequest.content());

        boolean updatePostContent = commentRepository.updateCommentContent(post);
        return getGenericCommentResult(updatePostContent);
    }

    private Comment getCommentInternal(Long commentId) {

        ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatusCode.valueOf(404), "Comment not found");

        return commentRepository.findCommentByCommentId(commentId)
                .orElseThrow(() -> responseStatusException);
    }

    @Override
    @Transactional
    public GenericCommentResult deleteComment(Long commentId, BlogUserPrincipal blogUserPrincipal) {

        Assert.notNull(blogUserPrincipal, BLOG_USER_PRINCIPAL_CANNOT_BE_NULL);

        getCommentInternal(commentId);
        boolean deletePost = commentRepository.deleteComment(commentId);
        return getGenericCommentResult(deletePost);
    }

    @Override
    @Transactional
    public ReadCommentResult readComment(Long postId) {
        return new ReadCommentResult(getCommentInternal(postId));
    }

    @Override
    @Transactional
    public ReadCommentsResult readAllCommentsByPostId(Long postId, ReadCommentsRequest readCommentsRequest) {

        Assert.notNull(readCommentsRequest, "ReadPostsRequest cannot be null");
        return new ReadCommentsResult(commentRepository.findAllCommentsByPostId(postId, readCommentsRequest));
    }

    @Override
    @Transactional
    public GenericCommentResult likeComment(Long commentId) {

        Comment comment = getCommentInternal(commentId);
        boolean likePostContent = commentRepository.likeCommentContent(comment);
        return getGenericCommentResult(likePostContent);
    }
}
