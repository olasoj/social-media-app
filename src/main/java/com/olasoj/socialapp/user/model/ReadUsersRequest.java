package com.olasoj.socialapp.user.model;

import com.olasoj.socialapp.post.model.PagingInfo;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

public final class ReadUsersRequest implements Serializable {
    private PagingInfo pagingInfo;

    public ReadUsersRequest(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public ReadUsersRequest() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ReadUsersRequest that)) return false;
        return Objects.equals(pagingInfo, that.pagingInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pagingInfo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ReadUsersRequest.class.getSimpleName() + "[", "]")
                .add("pagingInfo=" + pagingInfo)
                .toString();
    }

    public PagingInfo getPagingInfo() {
        return (pagingInfo == null) ? new PagingInfo() : pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }
}
