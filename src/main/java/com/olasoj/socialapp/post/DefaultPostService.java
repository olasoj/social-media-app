package com.olasoj.socialapp.post;

import com.olasoj.socialapp.post.model.*;
import com.olasoj.socialapp.author.model.Author;
import com.olasoj.socialapp.post.model.request.CreatePostRequest;
import com.olasoj.socialapp.post.model.request.EditPostRequest;
import com.olasoj.socialapp.post.repository.BlogRepository;
import com.olasoj.socialapp.post.repository.DefaultBlogRepository;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

@Component
public class DefaultPostService implements PostService {
    private static final String BLOG_USER_PRINCIPAL_CANNOT_BE_NULL = "BlogUserPrincipal cannot be null";
    private final BlogRepository blogRepository;

    public DefaultPostService() {
        this.blogRepository = DefaultBlogRepository.blogRepository;
    }

    @Override
    public CreatePostResult createPost(CreatePostRequest createPostRequest, BlogUserPrincipal blogUserPrincipal) {
        Assert.notNull(createPostRequest, "CreateBlogPostRequest cannot be null");
        Assert.notNull(blogUserPrincipal, BLOG_USER_PRINCIPAL_CANNOT_BE_NULL);

        Blog blog = Blog.builder()
                .title(createPostRequest.title())
                .body(createPostRequest.body())
                .author(new Author(blogUserPrincipal.user()))
                .build();

        blogRepository.saveBlogPost(blog);
        return new CreatePostResult(blog);
    }

    @Override
    public EditPostResult editPost(String blogId, EditPostRequest editPostRequest, BlogUserPrincipal blogUserPrincipal) {
        Assert.notNull(blogId, "BlogId cannot be null");
        Assert.notNull(editPostRequest, "EditBlogPostRequest cannot be null");
        Assert.notNull(blogUserPrincipal, BLOG_USER_PRINCIPAL_CANNOT_BE_NULL);

        Blog blog = validateBlogPostChange(blogId, blogUserPrincipal);

        blog.setTitle(editPostRequest.title());
        blog.setBody(editPostRequest.body());

        blogRepository.updateBlogTitle(blog);
        return new EditPostResult(blog);
    }

    private Blog validateBlogPostChange(String blogId, BlogUserPrincipal blogUserPrincipal) {

        ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatusCode.valueOf(404), "Blog post not found");

        Blog blog = blogRepository.findBlogTitle(blogId)
                .orElseThrow(() -> responseStatusException);

        if (!blog.getAuthor().getUserId().equals(blogUserPrincipal.user().getUserId()))
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "You do not have write permission to this resource");

        return blog;
    }

    @Override
    public DeletePostResult deletePost(String blogId, BlogUserPrincipal blogUserPrincipal) {
        Assert.notNull(blogUserPrincipal, BLOG_USER_PRINCIPAL_CANNOT_BE_NULL);

        validateBlogPostChange(blogId, blogUserPrincipal);

        blogRepository.deleteBlogTitle(blogId);

        return new DeletePostResult();
    }

    @Override
    public ReadPostResult readPost() {
        return new ReadPostResult(blogRepository.findAllBlogPost());
    }
}
