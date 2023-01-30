package com.slcube.shop.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.slcube.shop.common.exception.CustomErrorCode.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = INVALID_EMAIL_OR_PASSWORD.getErrorMessage();

        response.setStatus(UNAUTHORIZED.value());
        response.setContentType(APPLICATION_JSON_VALUE);

        if (exception instanceof BadCredentialsException) {
            errorMessage = INVALID_EMAIL_OR_PASSWORD.getErrorMessage();
        } else if (exception instanceof DisabledException) {
            errorMessage = DISABLED_MEMBER.getErrorMessage();
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = SESSION_EXPIRED.getErrorMessage();
        }

        objectMapper.writeValue(response.getWriter(), errorMessage);
    }
}
