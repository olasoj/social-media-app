package com.primebank.blog.blogpost.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.primebank.blog.blogpost.model.Blog;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBlogPostRequest {

    @NotBlank(message = "Enter title")
    @JsonProperty("title")
    private String title;

    @NotBlank(message = "Enter body")
    @JsonProperty("body")
    private String body;

    public String title() {
        return title;
    }

    public String body() {
        return body;
    }


    public Blog.BlogBuilder builder() {
        return new Blog.BlogBuilder();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof CreateBlogPostRequest otherBlog)) return false;

        return new EqualsBuilder()
                .append(title, otherBlog.title)
                .append(body, otherBlog.body)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(body)
                .toHashCode();
    }

}
