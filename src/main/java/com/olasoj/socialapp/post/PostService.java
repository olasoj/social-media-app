package com.olasoj.socialapp.post;

import com.olasoj.socialapp.post.model.*;
import com.olasoj.socialapp.post.model.request.CreatePostRequest;
import com.olasoj.socialapp.post.model.request.EditPostRequest;
import com.olasoj.socialapp.post.model.request.ReadPostsRequest;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;

public interface PostService {

    CreatePostResult createPost(CreatePostRequest createPostRequest, BlogUserPrincipal blogUserPrincipal);

    EditPostResult editPost(Long postId, EditPostRequest editPostRequest, BlogUserPrincipal blogUserPrincipal);

    DeletePostResult deletePost(Long postId, BlogUserPrincipal blogUserPrincipal);

    ReadPostResult readPost(Long postId);

    ReadPostsResult readAllPosts(ReadPostsRequest readPostsRequest);

}
