package com.ivanbasic.learnspring.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class StupidFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(StupidFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        LOG.debug("StupidFilter invoked for {} {}", request.getMethod(), request.getRequestURI());

        String stupidKey = request.getHeader("StupidKey");

        if (stupidKey != null) {
            LOG.warn("StupidKey detected -> rejecting request. StupidKey='{}'", stupidKey);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "StupidKey is not allowed");
            return; // breaks the filter chain
        }

        filterChain.doFilter(request, response); // continue normally
    }
}
