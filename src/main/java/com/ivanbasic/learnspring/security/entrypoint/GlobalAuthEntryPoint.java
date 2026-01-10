package com.ivanbasic.learnspring.security.entrypoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class GlobalAuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOG =
            LoggerFactory.getLogger(GlobalAuthEntryPoint.class);

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException ex) throws IOException {

        LOG.info("GLOBAL auth failed for {} {}", request.getMethod(), request.getRequestURI());

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
