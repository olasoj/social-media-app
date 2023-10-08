package com.olasoj.socialapp.post.repository;

import com.olasoj.socialapp.post.model.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogRepository {
    Blog saveBlogPost(Blog blog);

    Optional<Blog> findBlogTitle(String userId);

    List<Blog> findAllBlogPost();

    Optional<Blog> deleteBlogTitle(String userId);

    Blog updateBlogTitle(Blog blog);
}
