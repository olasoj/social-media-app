package com.olasoj.socialapp.registration;

import com.olasoj.socialapp.follow.service.FollowRelationshipService;
import com.olasoj.socialapp.user.UserService;
import com.olasoj.socialapp.user.model.BlogUser;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;
import com.olasoj.socialapp.user.model.BlogUserView;
import com.olasoj.socialapp.user.model.UserAndAccountInfo;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationInboundRestController {

    private final RegistrationService registrationService;
    private final UserService userService;
    private final FollowRelationshipService followRelationshipService;

    public RegistrationInboundRestController(RegistrationService registrationService, UserService userService, FollowRelationshipService followRelationshipService) {
        this.registrationService = registrationService;
        this.userService = userService;
        this.followRelationshipService = followRelationshipService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<Response<RegistrationResult>> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws URISyntaxException {
        RegistrationResult registrationResult = registrationService.registerUser(registrationRequest);
        Response<RegistrationResult> model = ResponseAssembler.toResponse(HttpStatus.CREATED, registrationResult);
        return ResponseEntity.created(new URI("")).body(model);
    }

    @GetMapping(path = "/")
    public ResponseEntity<Response<Map<String, Object>>> getUserDetails(@AuthenticationPrincipal BlogUserPrincipal blogUserPrincipal) throws URISyntaxException {

        Map<String, Object> result = new HashMap<>();
        UserAndAccountInfo userWithAccountInfo = userService.getUserWithAccountInfo(blogUserPrincipal.getUsername());

        result.put("user", new BlogUserView((BlogUser) userWithAccountInfo.getUser()));
        result.put("followers", followRelationshipService.getAllFollowers(userWithAccountInfo.getSocialAccountId()));
        result.put("following", followRelationshipService.getAllFollowing(userWithAccountInfo.getSocialAccountId()));

        Response<Map<String, Object>> model = ResponseAssembler.toResponse(HttpStatus.CREATED, result);
        return ResponseEntity.created(new URI("")).body(model);
    }

}
