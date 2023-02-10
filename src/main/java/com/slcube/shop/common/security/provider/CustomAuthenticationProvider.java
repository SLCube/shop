package com.slcube.shop.common.security.provider;

import com.slcube.shop.common.security.authenticationContext.MemberContext;
import com.slcube.shop.common.security.token.AuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, memberContext.getPassword())) {
            throw new BadCredentialsException("비밀번호가 틀렸습니다.");
        }

        return new AuthenticationToken(memberContext, memberContext.getPassword(), memberContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AuthenticationToken.class);
    }
}
