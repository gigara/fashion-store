package com.giga.FashionStore.security;

import com.giga.FashionStore.service.UserDetails;
import com.giga.FashionStore.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Authentication provider class to validate user credentials.
 */
@Component
public class GigaAuthProvider implements AuthenticationProvider {
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication auth)
            throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials()
                .toString();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails.getUsername().equals(username) &&
                encoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken
                    (userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        } else {
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}