package com.primebank.blog.blogpost.adapter;

import com.primebank.blog.blogpost.BlogService;
import com.primebank.blog.blogpost.model.CreateBlogPostResult;
import com.primebank.blog.blogpost.model.DeleteBlogPostResult;
import com.primebank.blog.blogpost.model.EditBlogPostResult;
import com.primebank.blog.blogpost.model.ReadBlogPostResult;
import com.primebank.blog.blogpost.model.request.CreateBlogPostRequest;
import com.primebank.blog.blogpost.model.request.EditBlogPostRequest;
import com.primebank.blog.user.model.BlogUserPrincipal;
import com.primebank.blog.util.response.model.Response;
import com.primebank.blog.util.response.transformer.ResponseAssembler;
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

    private final BlogService blogService;

    public BlogInboundRestController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<Response<CreateBlogPostResult>> createBlogPost(@Valid @RequestBody CreateBlogPostRequest createBlogPostRequest, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) throws URISyntaxException {
        CreateBlogPostResult createBlogPostResult = blogService.createBlogPost(createBlogPostRequest, blogUserPrincipal);
        Response<CreateBlogPostResult> model = ResponseAssembler.toResponse(HttpStatus.CREATED, createBlogPostResult);
        return ResponseEntity.created(new URI("")).body(model);
    }

    @GetMapping(path = "/")
    public ResponseEntity<Response<ReadBlogPostResult>> getUserDetails() {
        ReadBlogPostResult readBlogPostResult = blogService.readBlogPost();
        Response<ReadBlogPostResult> response = ResponseAssembler.toResponse(HttpStatus.OK, readBlogPostResult);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping(value = "/{blogId}")
    public ResponseEntity<Response<EditBlogPostResult>> editBlogPost(@PathVariable(value = "blogId") String blogId, @Valid @RequestBody EditBlogPostRequest editBlogPostRequest, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) {
        EditBlogPostResult editBlogPostResult = blogService.editBlogPost(blogId, editBlogPostRequest, blogUserPrincipal);
        Response<EditBlogPostResult> response = ResponseAssembler.toResponse(HttpStatus.OK, editBlogPostResult);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{blogId}")
    public ResponseEntity<Response<DeleteBlogPostResult>> deleteBlogPost(@PathVariable(value = "blogId") String blogId, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) {
        DeleteBlogPostResult deleteBlogPostResult = blogService.deleteBlogPost(blogId, blogUserPrincipal);
        Response<DeleteBlogPostResult> response = ResponseAssembler.toResponse(HttpStatus.OK, deleteBlogPostResult);
        return ResponseEntity.ok().body(response);
    }
}
