package com.olasoj.socialapp.auth.authenticaton;

import com.olasoj.socialapp.auth.jwt.model.CreateJwTokenRequest;
import com.olasoj.socialapp.auth.jwt.model.CreateJwTokenResult;
import com.olasoj.socialapp.auth.jwt.provider.JwtTokenProvider;
import com.olasoj.socialapp.auth.model.authentication.BlogAppUsernamePasswordAuthenticationToken;
import com.olasoj.socialapp.auth.tokenstore.AuthTokenStore;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;
import com.olasoj.socialapp.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BlogAppUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthTokenStore authTokenStore;


    public BlogAppUsernamePasswordAuthenticationProvider(@Qualifier("defaultUserService") UserService userService,
                                                         @Qualifier("bCryptPasswordEncoder") PasswordEncoder passwordEncoder,
                                                         @Qualifier("jwsJwtTokenProvider") JwtTokenProvider jwtTokenProvider,
                                                         @Qualifier("inMemoryAuthTokenStore") AuthTokenStore authTokenStore) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authTokenStore = authTokenStore;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        CreateJwTokenResult createJwTokenResult = auth(username, password);
        String sessionId = authTokenStore.create(createJwTokenResult.jwtToken());

            return new BlogAppUsernamePasswordAuthenticationToken(sessionId, sessionId);
    }

    private CreateJwTokenResult auth(String username, String password) {
        UserDetails userDetails = userService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Bad credentials.");

        if (!(userDetails instanceof BlogUserPrincipal userPrincipal)) throw new IllegalArgumentException("BlogUserPrincipal is required");

        CreateJwTokenRequest createJwTokenRequest = CreateJwTokenRequest.builder()
                .subject(username)
                .accessControlLists((userPrincipal).user().getAccessControlLists())
                .build();

        return jwtTokenProvider.createToken(createJwTokenRequest);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return BlogAppUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
