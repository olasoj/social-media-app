package com.olasoj.socialapp.post.adapter;

import com.olasoj.socialapp.post.PostService;
import com.olasoj.socialapp.post.model.DeletePostResult;
import com.olasoj.socialapp.post.model.ReadPostResult;
import com.olasoj.socialapp.util.response.model.Response;
import com.olasoj.socialapp.util.response.transformer.ResponseAssembler;
import com.olasoj.socialapp.post.model.CreatePostResult;
import com.olasoj.socialapp.post.model.EditPostResult;
import com.olasoj.socialapp.post.model.request.CreatePostRequest;
import com.olasoj.socialapp.post.model.request.EditPostRequest;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/blog-post", consumes = MediaType.APPLICATION_JSON_VALUE)
public class BlogInboundRestController {

    private final PostService postService;

    public BlogInboundRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<Response<CreatePostResult>> createBlogPost(@Valid @RequestBody CreatePostRequest createPostRequest, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) throws URISyntaxException {
        CreatePostResult createPostResult = postService.createPost(createPostRequest, blogUserPrincipal);
        Response<CreatePostResult> model = ResponseAssembler.toResponse(HttpStatus.CREATED, createPostResult);
        return ResponseEntity.created(new URI("")).body(model);
    }

    @GetMapping(path = "/")
    public ResponseEntity<Response<ReadPostResult>> getUserDetails() {
        ReadPostResult readPostResult = postService.readPost();
        Response<ReadPostResult> response = ResponseAssembler.toResponse(HttpStatus.OK, readPostResult);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping(value = "/{blogId}")
    public ResponseEntity<Response<EditPostResult>> editBlogPost(@PathVariable(value = "blogId") String blogId, @Valid @RequestBody EditPostRequest editPostRequest, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) {
        EditPostResult editPostResult = postService.editPost(blogId, editPostRequest, blogUserPrincipal);
        Response<EditPostResult> response = ResponseAssembler.toResponse(HttpStatus.OK, editPostResult);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{blogId}")
    public ResponseEntity<Response<DeletePostResult>> deleteBlogPost(@PathVariable(value = "blogId") String blogId, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) {
        DeletePostResult deletePostResult = postService.deletePost(blogId, blogUserPrincipal);
        Response<DeletePostResult> response = ResponseAssembler.toResponse(HttpStatus.OK, deletePostResult);
        return ResponseEntity.ok().body(response);
    }
}
