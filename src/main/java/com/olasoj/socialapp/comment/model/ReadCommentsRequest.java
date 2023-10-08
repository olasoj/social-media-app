package com.olasoj.socialapp.comment.model;

import com.olasoj.socialapp.post.model.PagingInfo;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

public final class ReadCommentsRequest implements Serializable {
    private String content;
    private PagingInfo pagingInfo;

    public ReadCommentsRequest(String content, PagingInfo pagingInfo) {
        this.content = content;
        this.pagingInfo = pagingInfo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ReadCommentsRequest that)) return false;
        return Objects.equals(content, that.content) && Objects.equals(pagingInfo, that.pagingInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, pagingInfo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ReadCommentsRequest.class.getSimpleName() + "[", "]")
                .add("content='" + content + "'")
                .add("pagingInfo=" + pagingInfo)
                .toString();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PagingInfo getPagingInfo() {
        return (pagingInfo == null) ? new PagingInfo() : pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }
}
