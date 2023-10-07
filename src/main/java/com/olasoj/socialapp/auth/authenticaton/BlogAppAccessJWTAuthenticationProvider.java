package com.olasoj.socialapp.auth.authenticaton;

import com.olasoj.socialapp.auth.jwt.model.JwToken;
import com.olasoj.socialapp.auth.jwt.model.JwTokenDecipherRequest;
import com.olasoj.socialapp.auth.jwt.provider.JwtTokenProvider;
import com.olasoj.socialapp.auth.model.authentication.AccessJWTAuthenticationToken;
import com.olasoj.socialapp.auth.tokenstore.AuthTokenStore;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;
import com.olasoj.socialapp.auth.tokenstore.StoreAuthToken;
import com.olasoj.socialapp.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class BlogAppAccessJWTAuthenticationProvider implements AuthenticationProvider {
    private final AuthTokenStore authTokenStore;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public BlogAppAccessJWTAuthenticationProvider(@Qualifier("inMemoryAuthTokenStore") AuthTokenStore authTokenStore,
                                                  @Qualifier("jwsJwtTokenProvider") JwtTokenProvider jwtTokenProvider,
                                                  UserService userService) {
        this.authTokenStore = authTokenStore;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String sessionId = String.valueOf(authentication.getCredentials());

        Optional<StoreAuthToken> storeAuthToken = authTokenStore.read(sessionId);

        StoreAuthToken accessToken = storeAuthToken.orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(401), "Auth token not found"));

        JwToken jwToken = readJwtToken(accessToken.accessToken());

        UserDetails userDetails = userService.loadUserByUsername(jwToken.subject());

        if (!(userDetails instanceof BlogUserPrincipal userPrincipal)) throw new IllegalArgumentException("BlogUserPrincipal is required");

        return new AccessJWTAuthenticationToken(userPrincipal, sessionId, userPrincipal.getAuthorities());
    }

    private JwToken readJwtToken(String accessToken) {
        JwTokenDecipherRequest jwTokenDecipherRequest = new JwTokenDecipherRequest(accessToken);
        return jwtTokenProvider.readToken(jwTokenDecipherRequest);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AccessJWTAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
