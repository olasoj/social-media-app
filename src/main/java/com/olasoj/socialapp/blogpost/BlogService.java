package com.olasoj.socialapp.blogpost;

import com.olasoj.socialapp.blogpost.model.DeleteBlogPostResult;
import com.olasoj.socialapp.blogpost.model.ReadBlogPostResult;
import com.olasoj.socialapp.blogpost.model.CreateBlogPostResult;
import com.olasoj.socialapp.blogpost.model.EditBlogPostResult;
import com.olasoj.socialapp.blogpost.model.request.CreateBlogPostRequest;
import com.olasoj.socialapp.blogpost.model.request.EditBlogPostRequest;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;

public interface BlogService {

    CreateBlogPostResult createBlogPost(CreateBlogPostRequest createBlogPostRequest, BlogUserPrincipal blogUserPrincipal);

    EditBlogPostResult editBlogPost(String blogId, EditBlogPostRequest editBlogPostRequest, BlogUserPrincipal blogUserPrincipal);

    DeleteBlogPostResult deleteBlogPost(String blogId, BlogUserPrincipal blogUserPrincipal);

    ReadBlogPostResult readBlogPost();
}
