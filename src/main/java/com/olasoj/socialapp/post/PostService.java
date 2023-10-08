package com.olasoj.socialapp.post;

import com.olasoj.socialapp.post.model.*;
import com.olasoj.socialapp.post.model.request.CreatePostRequest;
import com.olasoj.socialapp.post.model.request.EditPostRequest;
import com.olasoj.socialapp.post.model.request.ReadPostsRequest;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;
import org.springframework.transaction.annotation.Transactional;

public interface PostService {

    GenericPostResult createPost(CreatePostRequest createPostRequest, BlogUserPrincipal blogUserPrincipal);

    GenericPostResult editPost(Long postId, EditPostRequest editPostRequest, BlogUserPrincipal blogUserPrincipal);

    GenericPostResult deletePost(Long postId, BlogUserPrincipal blogUserPrincipal);

    ReadPostResult readPost(Long postId);

    ReadPostsResult readAllPosts(ReadPostsRequest readPostsRequest);

    GenericPostResult likePost(Long postId);
}
