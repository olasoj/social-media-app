package com.olasoj.socialapp.follow.adapter;

import com.olasoj.socialapp.follow.service.FollowRelationshipService;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;
import com.olasoj.socialapp.util.response.model.Response;
import com.olasoj.socialapp.util.response.transformer.ResponseAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/follow", consumes = MediaType.APPLICATION_JSON_VALUE)
public class FollowInboundRestController {

    private final FollowRelationshipService registrationService;

    public FollowInboundRestController(FollowRelationshipService registrationService) {
        this.registrationService = registrationService;
    }

    @PutMapping(path = "/{socialAccountId}")
    public ResponseEntity<Response<Boolean>> toggleFollowStatus(@PathVariable(value = "socialAccountId") Long socialAccountId, @AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) throws URISyntaxException {
        boolean toggleFollowStatus = registrationService.toggleFollowStatus(socialAccountId, blogUserPrincipal);
        Response<Boolean> model = ResponseAssembler.toResponse(HttpStatus.CREATED, toggleFollowStatus);
        return ResponseEntity.created(new URI("")).body(model);
    }

}
