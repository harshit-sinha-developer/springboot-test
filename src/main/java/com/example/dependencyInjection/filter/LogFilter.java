package com.example.dependencyInjection.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LogFilter extends OncePerRequestFilter {
    private static Logger logger = LoggerFactory.getLogger(LogFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Long startTime = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        Long endTime = System.currentTimeMillis();
        logger.info("Received a request URL: " + request.getRequestURI() + " Method: " + request.getMethod() + " Time taken: " + (endTime - startTime) + " ms");
    }
}
