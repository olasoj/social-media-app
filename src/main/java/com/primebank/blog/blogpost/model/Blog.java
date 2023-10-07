package com.primebank.blog.blogpost.model;

import com.primebank.blog.author.model.Author;
import com.primebank.blog.util.security.Utils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.util.Assert;

public class Blog {

    private final String blogId;
    private final Author author;
    private String title;
    private String body;

    public Blog(BlogBuilder blogBuilder) {
        Assert.notNull(blogBuilder, "Blog builder cannot be null");

        this.blogId = Utils.randomId();

        Assert.notNull(blogBuilder.title, "Blog title cannot be null");
        this.title = blogBuilder.title;

        Assert.notNull(blogBuilder.body, "body title cannot be null");
        this.body = blogBuilder.body;

        Assert.notNull(blogBuilder.author, "body author cannot be null");
        this.author = blogBuilder.author;
    }

    public static BlogBuilder builder() {
        return new BlogBuilder();
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Author getAuthor() {
        return author;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Blog otherBlog)) return false;

        return new EqualsBuilder()
                .append(title, otherBlog.title)
                .append(body, otherBlog.body)
                .append(author, otherBlog.author)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(body)
                .append(author)
                .toHashCode();
    }

    public static class BlogBuilder {
        private String title;
        private String body;
        private Author author;

        public BlogBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BlogBuilder body(String body) {
            this.body = body;
            return this;
        }

        public BlogBuilder author(Author author) {
            this.author = author;
            return this;
        }

        public Blog build() {
            return new Blog(this);
        }
    }
}
