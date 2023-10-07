package com.primebank.blog.user.model;

import com.primebank.blog.user.acl.AccessControlList;
import com.primebank.blog.util.security.Utils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BlogUser implements User {
    private String userId;
    private String username;
    private String password;
    private List<AccessControlList> accessControlList;

    public BlogUser(BlogUserBuilder blogUserBuilder) {
        Assert.notNull(blogUserBuilder, "Blog builder cannot be null");

        this.userId = Utils.randomId();

        Assert.notNull(blogUserBuilder.username, "User username cannot be null");
        this.username = blogUserBuilder.username;

        Assert.notNull(blogUserBuilder.password, "User password cannot be null");
        this.password = blogUserBuilder.password;

        Assert.notNull(blogUserBuilder.accessControlList, "User accessControlList cannot be null");
        this.accessControlList = blogUserBuilder.accessControlList;

    }

    public static BlogUserBuilder builder() {
        return new BlogUserBuilder();
    }

    public BlogUser setAccessControlList(List<AccessControlList> accessControlList) {
        this.accessControlList = accessControlList;
        return this;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public BlogUser setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public BlogUser setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public BlogUser setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public List<AccessControlList> getAccessControlLists() {
        return Objects.isNull(accessControlList) ? Collections.emptyList() : accessControlList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof BlogUser otherBlogUser)) return false;

        return new EqualsBuilder()
                .append(userId, otherBlogUser.userId)
                .append(username, otherBlogUser.username)
                .append(password, otherBlogUser.password)
                .append(accessControlList, otherBlogUser.accessControlList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(userId).append(username).append(password).append(accessControlList).toHashCode();
    }

    public static class BlogUserBuilder extends AbstractUserBuilder<BlogUser, BlogUserBuilder> {

        @Override
        protected BlogUserBuilder self() {
            return this;
        }

        @Override
        public BlogUser build() {
            return new BlogUser(this);
        }
    }

}
