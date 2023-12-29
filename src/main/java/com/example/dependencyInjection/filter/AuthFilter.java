package com.example.dependencyInjection.filter;

import com.example.dependencyInjection.exception.ApiException;
import com.example.dependencyInjection.exception.AuthenticationException;
import com.example.dependencyInjection.model.ApiError;
import com.example.dependencyInjection.model.UserSession;
import com.example.dependencyInjection.repository.UserSessionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@Order(2)
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private UserSessionRepository userSessionRepository;

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private static final String[] excludedEndpoints = {"/auth", "/ping"};

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestURI = request.getRequestURI();

        return Arrays.stream(excludedEndpoints).anyMatch(requestURI::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            String token = fetchTokenFromRequest(request);

            Optional<UserSession> optionalUserSession = userSessionRepository.findById(token);

            if (optionalUserSession.isEmpty()) {
                throw new AuthenticationException("Bearer token not found.");
            }

            filterChain.doFilter(request, response);
        } catch (ApiException apiException) {
            ApiError apiError = new ApiError(apiException.getMessage(), apiException.getCode());
            response.setStatus(apiException.getResponseStatus().value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(convertObjectToJson(apiError));
        }
    }

    private String fetchTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if(bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            throw new AuthenticationException("Bearer token not found.");
        }

        return bearerToken.split(" ")[1];
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
