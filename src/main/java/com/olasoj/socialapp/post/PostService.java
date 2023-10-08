package com.olasoj.socialapp.post;

import com.olasoj.socialapp.post.model.DeletePostResult;
import com.olasoj.socialapp.post.model.ReadPostResult;
import com.olasoj.socialapp.post.model.CreatePostResult;
import com.olasoj.socialapp.post.model.EditPostResult;
import com.olasoj.socialapp.post.model.request.CreatePostRequest;
import com.olasoj.socialapp.post.model.request.EditPostRequest;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;

public interface PostService {

    CreatePostResult createPost(CreatePostRequest createPostRequest, BlogUserPrincipal blogUserPrincipal);

    EditPostResult editPost(String blogId, EditPostRequest editPostRequest, BlogUserPrincipal blogUserPrincipal);

    DeletePostResult deletePost(String blogId, BlogUserPrincipal blogUserPrincipal);

    ReadPostResult readPost();
}
