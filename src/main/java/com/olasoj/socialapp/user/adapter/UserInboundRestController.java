package com.olasoj.socialapp.user.adapter;

import com.olasoj.socialapp.user.UserService;
import com.olasoj.socialapp.user.model.BlogUser;
import com.olasoj.socialapp.user.model.BlogUserView;
import com.olasoj.socialapp.user.model.ReadUsersRequest;
import com.olasoj.socialapp.user.model.UserWithPageInfoResult;
import com.olasoj.socialapp.util.response.model.Response;
import com.olasoj.socialapp.util.response.transformer.ResponseAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserInboundRestController {

    private final UserService userService;

    public UserInboundRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<Response<Map<String, Object>>> getAllUsers(@RequestBody ReadUsersRequest readUsersRequest) throws URISyntaxException {

        Map<String, Object> result = new HashMap<>();
        UserWithPageInfoResult allUserWithAccountInfo = userService.getAllUserWithAccountInfo(readUsersRequest);

        List<BlogUserView> blogUserViews = allUserWithAccountInfo.userAndAccountInfos()
                .stream()
                .map(userAndAccountInfo -> new BlogUserView((BlogUser) userAndAccountInfo.getUser()))
                .toList();

        result.put("users", blogUserViews);
        result.put("pagingInfo", allUserWithAccountInfo.pagingInfo());

        Response<Map<String, Object>> model = ResponseAssembler.toResponse(HttpStatus.CREATED, result);
        return ResponseEntity.created(new URI("")).body(model);
    }

}
