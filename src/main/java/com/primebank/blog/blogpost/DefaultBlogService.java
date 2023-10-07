package com.primebank.blog.blogpost;

import com.primebank.blog.author.model.Author;
import com.primebank.blog.blogpost.model.*;
import com.primebank.blog.blogpost.model.request.CreateBlogPostRequest;
import com.primebank.blog.blogpost.model.request.EditBlogPostRequest;
import com.primebank.blog.blogpost.repository.BlogRepository;
import com.primebank.blog.blogpost.repository.DefaultBlogRepository;
import com.primebank.blog.user.model.BlogUserPrincipal;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

@Component
public class DefaultBlogService implements BlogService {
    private static final String BLOG_USER_PRINCIPAL_CANNOT_BE_NULL = "BlogUserPrincipal cannot be null";
    private final BlogRepository blogRepository;

    public DefaultBlogService() {
        this.blogRepository = DefaultBlogRepository.blogRepository;
    }

    @Override
    public CreateBlogPostResult createBlogPost(CreateBlogPostRequest createBlogPostRequest, BlogUserPrincipal blogUserPrincipal) {
        Assert.notNull(createBlogPostRequest, "CreateBlogPostRequest cannot be null");
        Assert.notNull(blogUserPrincipal, BLOG_USER_PRINCIPAL_CANNOT_BE_NULL);

        Blog blog = Blog.builder()
                .title(createBlogPostRequest.title())
                .body(createBlogPostRequest.body())
                .author(new Author(blogUserPrincipal.user()))
                .build();

        blogRepository.saveBlogPost(blog);
        return new CreateBlogPostResult(blog);
    }

    @Override
    public EditBlogPostResult editBlogPost(String blogId, EditBlogPostRequest editBlogPostRequest, BlogUserPrincipal blogUserPrincipal) {
        Assert.notNull(blogId, "BlogId cannot be null");
        Assert.notNull(editBlogPostRequest, "EditBlogPostRequest cannot be null");
        Assert.notNull(blogUserPrincipal, BLOG_USER_PRINCIPAL_CANNOT_BE_NULL);

        Blog blog = validateBlogPostChange(blogId, blogUserPrincipal);

        blog.setTitle(editBlogPostRequest.title());
        blog.setBody(editBlogPostRequest.body());

        blogRepository.updateBlogTitle(blog);
        return new EditBlogPostResult(blog);
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
    public DeleteBlogPostResult deleteBlogPost(String blogId, BlogUserPrincipal blogUserPrincipal) {
        Assert.notNull(blogUserPrincipal, BLOG_USER_PRINCIPAL_CANNOT_BE_NULL);

        validateBlogPostChange(blogId, blogUserPrincipal);

        blogRepository.deleteBlogTitle(blogId);

        return new DeleteBlogPostResult();
    }

    @Override
    public ReadBlogPostResult readBlogPost() {
        return new ReadBlogPostResult(blogRepository.findAllBlogPost());
    }
}
