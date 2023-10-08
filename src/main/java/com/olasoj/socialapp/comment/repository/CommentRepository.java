package com.olasoj.socialapp.comment.repository;

import com.olasoj.socialapp.comment.model.Comment;
import com.olasoj.socialapp.comment.model.CommentWithPageInfoResult;
import com.olasoj.socialapp.comment.model.ReadCommentsRequest;

import java.util.Optional;

public interface CommentRepository {
    boolean saveComment(Comment comment);

    Optional<Comment> findCommentByCommentId(Long commentId);

    CommentWithPageInfoResult findAllCommentsByPostId(Long postId, ReadCommentsRequest readCommentsRequest);

    boolean deleteComment(Long commentId);

    boolean updateCommentContent(Comment comment);

    boolean likeCommentContent(Comment comment);
}
