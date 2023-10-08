package com.olasoj.socialapp.comment.adapter;

import com.olasoj.socialapp.comment.model.GenericCommentResult;
import com.olasoj.socialapp.comment.model.ReadCommentResult;
import com.olasoj.socialapp.comment.model.ReadCommentsRequest;
import com.olasoj.socialapp.comment.model.ReadCommentsResult;
import com.olasoj.socialapp.comment.model.request.CreateCommentRequest;
import com.olasoj.socialapp.comment.model.request.EditCommentRequest;
import com.olasoj.socialapp.comment.service.CommentService;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;
import com.olasoj.socialapp.util.response.model.Response;
import com.olasoj.socialapp.util.response.transformer.ResponseAssembler;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
public class CommentInboundRestController {

    private final CommentService commentService;

    public CommentInboundRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/{postId}")
    public ResponseEntity<Response<GenericCommentResult>> createComment(@PathVariable(value = "postId") Long postId, @Valid @RequestBody CreateCommentRequest createCommentRequest, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) throws URISyntaxException {
        GenericCommentResult createCommentResult = commentService.createComment(postId, createCommentRequest, blogUserPrincipal);
        Response<GenericCommentResult> model = ResponseAssembler.toResponse(HttpStatus.CREATED, createCommentResult);
        return ResponseEntity.created(new URI("")).body(model);
    }

    @GetMapping(path = "/{commentId}")
    public ResponseEntity<Response<ReadCommentResult>> getUserDetails(@PathVariable(value = "commentId") Long commentId) {
        ReadCommentResult readCommentsResult = commentService.readComment(commentId);
        Response<ReadCommentResult> response = ResponseAssembler.toResponse(HttpStatus.OK, readCommentsResult);

        return ResponseEntity
                .ok()
                .body(response);
    }


    @GetMapping(path = "/by-post/{postId}")
    public ResponseEntity<Response<ReadCommentsResult>> readAllComments(@PathVariable(value = "postId") Long postId, @NotNull ReadCommentsRequest readCommentsRequest) {
        ReadCommentsResult readCommentsResult = commentService.readAllCommentsByPostId(postId, readCommentsRequest);
        Response<ReadCommentsResult> response = ResponseAssembler.toResponse(HttpStatus.OK, readCommentsResult);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping(value = "/{commentId}")
    public ResponseEntity<Response<GenericCommentResult>> editComment(@PathVariable(value = "commentId") Long commentId, @Valid @RequestBody EditCommentRequest editCommentRequest, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) {
        GenericCommentResult editCommentResult = commentService.editComment(commentId, editCommentRequest, blogUserPrincipal);
        Response<GenericCommentResult> response = ResponseAssembler.toResponse(HttpStatus.OK, editCommentResult);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{commentId}")
    public ResponseEntity<Response<GenericCommentResult>> deleteComment(@PathVariable(value = "commentId") Long commentId, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) {
        GenericCommentResult deleteCommentResult = commentService.deleteComment(commentId, blogUserPrincipal);
        Response<GenericCommentResult> response = ResponseAssembler.toResponse(HttpStatus.OK, deleteCommentResult);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/like/{commentId}")
    public ResponseEntity<Response<GenericCommentResult>> likeComment(@PathVariable(value = "commentId") Long commentId, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) {
        GenericCommentResult deleteCommentResult = commentService.likeComment(commentId);
        Response<GenericCommentResult> response = ResponseAssembler.toResponse(HttpStatus.OK, deleteCommentResult);
        return ResponseEntity.ok().body(response);
    }
}
