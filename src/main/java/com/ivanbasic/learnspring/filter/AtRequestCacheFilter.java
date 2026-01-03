package com.ivanbasic.learnspring.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AtRequestCacheFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(AtRequestCacheFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        LOG.info(
                "[Filter-AT-RequestCacheAwareFilter] method={} uri={}",
                request.getMethod(),
                request.getRequestURI()
        );

        filterChain.doFilter(request, response);
    }
}
