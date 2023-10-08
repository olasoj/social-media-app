package com.olasoj.socialapp.post;

import com.olasoj.socialapp.post.model.*;
import com.olasoj.socialapp.post.model.request.CreatePostRequest;
import com.olasoj.socialapp.post.model.request.EditPostRequest;
import com.olasoj.socialapp.post.model.request.ReadPostsRequest;
import com.olasoj.socialapp.post.repository.PostRepository;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Service
public class DefaultPostService implements PostService {
    private static final String BLOG_USER_PRINCIPAL_CANNOT_BE_NULL = "BlogUserPrincipal cannot be null";
    private final PostRepository postRepository;

    public DefaultPostService(@Qualifier("defaultPostRepository") PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public CreatePostResult createPost(CreatePostRequest createPostRequest, BlogUserPrincipal blogUserPrincipal) {
        Assert.notNull(createPostRequest, "CreateBlogPostRequest cannot be null");
        Assert.notNull(blogUserPrincipal, BLOG_USER_PRINCIPAL_CANNOT_BE_NULL);

        Post post = Post.builder()
                .content(createPostRequest.content())
                .likeCount(0)
                .socialMediaAccountId(2L) //TODO fix me
                .build();

        postRepository.saveBlogPost(post);
        return new CreatePostResult(post);
    }

    @Override
    @Transactional
    public EditPostResult editPost(Long postId, EditPostRequest editPostRequest, BlogUserPrincipal blogUserPrincipal) {

        Assert.notNull(postId, "BlogId cannot be null");
        Assert.notNull(editPostRequest, "EditBlogPostRequest cannot be null");
        Assert.notNull(blogUserPrincipal, BLOG_USER_PRINCIPAL_CANNOT_BE_NULL);

        Post post = getPostInternal(postId);

        post.setContent(editPostRequest.content());

        postRepository.updatePostContent(post);
        return new EditPostResult(post);
    }

    private Post getPostInternal(Long postId) {

        ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatusCode.valueOf(404), "Post not found");

        return postRepository.findPostByPostId(postId)
                .orElseThrow(() -> responseStatusException);
    }

    @Override
    @Transactional
    public DeletePostResult deletePost(Long postId, BlogUserPrincipal blogUserPrincipal) {
        Assert.notNull(blogUserPrincipal, BLOG_USER_PRINCIPAL_CANNOT_BE_NULL);

        getPostInternal(postId);

        postRepository.deletePost(postId);

        return new DeletePostResult();
    }

    @Override
    public ReadPostResult readPost(Long postId) {
        return new ReadPostResult(getPostInternal(postId));
    }

    @Override
    @Transactional
    public ReadPostsResult readAllPosts(ReadPostsRequest readPostsRequest) {

        Assert.notNull(readPostsRequest, "ReadPostsRequest cannot be null");
        return new ReadPostsResult(postRepository.findAllPost(readPostsRequest));
    }
}
