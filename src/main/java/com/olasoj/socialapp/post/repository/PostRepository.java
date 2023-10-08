package com.olasoj.socialapp.post.repository;

import com.olasoj.socialapp.post.model.Post;
import com.olasoj.socialapp.post.model.PostWithPageInfoResult;
import com.olasoj.socialapp.post.model.request.ReadPostsRequest;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    boolean saveBlogPost(Post post);

    Optional<Post> findPostByPostId(Long postId);

    PostWithPageInfoResult findAllPost(ReadPostsRequest readPostsRequest);

    boolean deletePost(Long userId);

    boolean updatePostContent(Post post);

    boolean likePostContent(Post post);
}
