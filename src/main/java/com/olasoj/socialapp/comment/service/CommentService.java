package com.olasoj.socialapp.comment.service;

import com.olasoj.socialapp.comment.model.GenericCommentResult;
import com.olasoj.socialapp.comment.model.ReadCommentResult;
import com.olasoj.socialapp.comment.model.ReadCommentsRequest;
import com.olasoj.socialapp.comment.model.ReadCommentsResult;
import com.olasoj.socialapp.comment.model.request.CreateCommentRequest;
import com.olasoj.socialapp.comment.model.request.EditCommentRequest;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;

public interface CommentService {

    GenericCommentResult createComment(Long postId, CreateCommentRequest createPostRequest, BlogUserPrincipal blogUserPrincipal);

    GenericCommentResult editComment(Long commentId, EditCommentRequest editPostRequest, BlogUserPrincipal blogUserPrincipal);

    GenericCommentResult deleteComment(Long postId, BlogUserPrincipal blogUserPrincipal);

    ReadCommentResult readComment(Long postId);

    ReadCommentsResult readAllCommentsByPostId(Long postId, ReadCommentsRequest readCommentsRequest);

    GenericCommentResult likeComment(Long commentId);
}
