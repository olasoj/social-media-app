package com.olasoj.socialapp.comment.model;

import com.olasoj.socialapp.post.model.PagingInfo;

import java.util.List;

public record CommentWithPageInfoResult(List<Comment> posts, PagingInfo pagingInfo) {
}
