package com.olasoj.socialapp.post.model;

import java.util.List;

public record PostWithPageInfoResult(List<Post> posts, PagingInfo pagingInfo) {
}
