package com.slcube.shop.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slcube.shop.business.member.dto.MemberLoginDto;
import com.slcube.shop.common.security.token.AuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public LoginProcessingFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/api/login"), authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (!isXmlHttpRequest(request)) {
            throw new IllegalStateException("지원되지 않는 인증방식입니다.");
        }

        MemberLoginDto memberLoginDto = objectMapper.readValue(request.getReader(), MemberLoginDto.class);

        if (!StringUtils.hasText(memberLoginDto.getEmail()) || !StringUtils.hasText(memberLoginDto.getPassword())) {
            throw new IllegalArgumentException("아이디와 비밀번호를 모두 입력해주세요.");
        }

        AuthenticationToken authenticationToken = new AuthenticationToken(memberLoginDto.getEmail(), memberLoginDto.getPassword());

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    private boolean isXmlHttpRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
