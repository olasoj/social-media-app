package com.olasoj.socialapp.post.repository;

import com.olasoj.socialapp.post.model.Blog;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultBlogRepository implements BlogRepository {

    public static final BlogRepository blogRepository = new DefaultBlogRepository();
    private static final String BLOG_POST_ID_CANNOT_BE_NULL = "Blog post id cannot be null";
    private final ConcurrentMap<String, Blog> blogPost;

    private DefaultBlogRepository() {
        blogPost = new ConcurrentHashMap<>();
    }

    @Override
    public Blog saveBlogPost(Blog blog) {
        Assert.notNull(blog, "Blog post cannot be null");

        synchronized (this.blogPost) {
            return blogPost.putIfAbsent(blog.getBlogId(), blog);
        }
    }

    @Override
    public Optional<Blog> findBlogTitle(String title) {
        Assert.notNull(title, BLOG_POST_ID_CANNOT_BE_NULL);

        return Optional.ofNullable(blogPost.get(title));
    }

    @Override
    public List<Blog> findAllBlogPost() {

        return new ArrayList<>(blogPost.values());
    }

    @Override
    public Optional<Blog> deleteBlogTitle(String title) {
        Assert.notNull(title, BLOG_POST_ID_CANNOT_BE_NULL);

        synchronized (this.blogPost) {
            return Optional.ofNullable(blogPost.remove(title));
        }
    }

    @Override
    public Blog updateBlogTitle(Blog blog) {
        Assert.notNull(blog, "Blog post cannot be null");

        synchronized (this.blogPost) {
            return blogPost.put(blog.getBlogId(), blog);
        }
    }
}
