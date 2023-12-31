package com.olasoj.socialapp.post.adapter;

import com.olasoj.socialapp.post.PostService;
import com.olasoj.socialapp.post.model.GenericPostResult;
import com.olasoj.socialapp.post.model.ReadPostResult;
import com.olasoj.socialapp.post.model.ReadPostsResult;
import com.olasoj.socialapp.post.model.request.CreatePostRequest;
import com.olasoj.socialapp.post.model.request.EditPostRequest;
import com.olasoj.socialapp.post.model.request.ReadPostsRequest;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;
import com.olasoj.socialapp.util.response.model.Response;
import com.olasoj.socialapp.util.response.transformer.ResponseAssembler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
public class PostInboundRestController {

    private final PostService postService;

    public PostInboundRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<Response<GenericPostResult>> createPost(@Valid @RequestBody CreatePostRequest createPostRequest, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) throws URISyntaxException {
        GenericPostResult createPostResult = postService.createPost(createPostRequest, blogUserPrincipal);
        Response<GenericPostResult> model = ResponseAssembler.toResponse(HttpStatus.CREATED, createPostResult);
        return ResponseEntity.created(new URI("")).body(model);
    }

    @GetMapping(path = "/{postId}")
    public ResponseEntity<Response<ReadPostResult>> getUserDetails(@PathVariable(value = "postId") Long postId) {
        ReadPostResult readPostsResult = postService.readPost(postId);
        Response<ReadPostResult> response = ResponseAssembler.toResponse(HttpStatus.OK, readPostsResult);

        return ResponseEntity
                .ok()
                .body(response);
    }


    @GetMapping(path = "/")
    public ResponseEntity<Response<ReadPostsResult>> readAllPosts(@RequestBody ReadPostsRequest readPostsRequest) {
        ReadPostsResult readPostsResult = postService.readAllPosts(readPostsRequest);
        Response<ReadPostsResult> response = ResponseAssembler.toResponse(HttpStatus.OK, readPostsResult);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping(value = "/{postId}")
    public ResponseEntity<Response<GenericPostResult>> editPost(@PathVariable(value = "postId") Long postId, @Valid @RequestBody EditPostRequest editPostRequest, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) {
        GenericPostResult editPostResult = postService.editPost(postId, editPostRequest, blogUserPrincipal);
        Response<GenericPostResult> response = ResponseAssembler.toResponse(HttpStatus.OK, editPostResult);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{postId}")
    public ResponseEntity<Response<GenericPostResult>> deletePost(@PathVariable(value = "postId") Long postId, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) {
        GenericPostResult deletePostResult = postService.deletePost(postId, blogUserPrincipal);
        Response<GenericPostResult> response = ResponseAssembler.toResponse(HttpStatus.OK, deletePostResult);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/like/{postId}")
    public ResponseEntity<Response<GenericPostResult>> likePost(@PathVariable(value = "postId") Long postId, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) {
        GenericPostResult deletePostResult = postService.likePost(postId);
        Response<GenericPostResult> response = ResponseAssembler.toResponse(HttpStatus.OK, deletePostResult);
        return ResponseEntity.ok().body(response);
    }
}
