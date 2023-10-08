package com.olasoj.socialapp.comment.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.olasoj.socialapp.post.model.Post;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCommentRequest {

    @NotBlank(message = "Enter content")
    @JsonProperty("content")
    private String content;

    public String content() {
        return content;
    }


    public Post.PostBuilder builder() {
        return new Post.PostBuilder();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof CreateCommentRequest otherBlog)) return false;

        return new EqualsBuilder()
                .append(content, otherBlog.content)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(content)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CreateCommentRequest.class.getSimpleName() + "[", "]")
                .add("content='" + content + "'")
                .toString();
    }
}
