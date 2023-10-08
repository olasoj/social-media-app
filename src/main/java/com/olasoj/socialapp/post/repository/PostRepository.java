package com.olasoj.socialapp.post.repository;

import com.olasoj.socialapp.post.model.Post;
import com.olasoj.socialapp.post.model.PostWithPageInfoResult;
import com.olasoj.socialapp.post.model.request.ReadPostsRequest;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post saveBlogPost(Post post);

    Optional<Post> findPostByPostId(Long postId);

    PostWithPageInfoResult findAllPost(ReadPostsRequest readPostsRequest);

    Optional<Post> deletePost(Long userId);

    Post updatePostContent(Post post);
}
