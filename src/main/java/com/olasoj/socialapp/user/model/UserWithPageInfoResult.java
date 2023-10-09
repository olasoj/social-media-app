package com.olasoj.socialapp.user.model;

import com.olasoj.socialapp.post.model.PagingInfo;

import java.util.List;

public record UserWithPageInfoResult(List<UserAndAccountInfo> userAndAccountInfos, PagingInfo pagingInfo) {
}
