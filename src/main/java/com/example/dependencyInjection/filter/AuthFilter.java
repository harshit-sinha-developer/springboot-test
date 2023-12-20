package com.example.dependencyInjection.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@Order(2)
public class AuthFilter extends OncePerRequestFilter {
    @Value("${app.jwt_secret}")
    private String jwtSecret;

    private static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private static String[] excludedEndpoints = {"/auth", "/ping"};
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return Arrays.stream(excludedEndpoints).anyMatch(endpoint -> request.getRequestURI().startsWith(endpoint));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.info("We are in Auth filter");
        if(request.getRequestURI().startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        String bearerToken = request.getHeader("Authorization");

        if(bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        String token = bearerToken.split(" ")[1];

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        if(decodedJWT == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(request, response);
        logger.info("We are exiting Auth filter");
    }
}
