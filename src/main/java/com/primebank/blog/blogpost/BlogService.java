package com.primebank.blog.blogpost;

import com.primebank.blog.blogpost.model.CreateBlogPostResult;
import com.primebank.blog.blogpost.model.DeleteBlogPostResult;
import com.primebank.blog.blogpost.model.EditBlogPostResult;
import com.primebank.blog.blogpost.model.ReadBlogPostResult;
import com.primebank.blog.blogpost.model.request.CreateBlogPostRequest;
import com.primebank.blog.blogpost.model.request.EditBlogPostRequest;
import com.primebank.blog.user.model.BlogUserPrincipal;

public interface BlogService {

    CreateBlogPostResult createBlogPost(CreateBlogPostRequest createBlogPostRequest, BlogUserPrincipal blogUserPrincipal);

    EditBlogPostResult editBlogPost(String blogId, EditBlogPostRequest editBlogPostRequest, BlogUserPrincipal blogUserPrincipal);

    DeleteBlogPostResult deleteBlogPost(String blogId, BlogUserPrincipal blogUserPrincipal);

    ReadBlogPostResult readBlogPost();
}
