package com.example.dependencyInjection.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.dependencyInjection.exception.AuthenticationException;
import com.example.dependencyInjection.model.*;
import com.example.dependencyInjection.repository.UserRepository;
import com.example.dependencyInjection.repository.UserSessionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signup(@RequestBody SignupRequest signupRequest) {
        String encryptedPassword = BCrypt.hashpw(signupRequest.getPassword(), BCrypt.gensalt());

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(encryptedPassword);
        user.setName(signupRequest.getName());

        userRepository.save(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

        if(optionalUser.isEmpty()) {
            throw new AuthenticationException("User does not exists.");
        }

        User user = optionalUser.get();

        if(BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {
            UserSession userSession = new UserSession();
            userSession.setUserId(user.getId());
            userSession = userSessionRepository.save(userSession);
            return new LoginResponse(userSession.getId());
        }

        throw new AuthenticationException("Failed to authenticate the user.");
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String bearerToken = request.getHeader("Authorization");

        if(bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            throw new AuthenticationException("Bearer token not found.");
        }

        String token = bearerToken.split(" ")[1];

        Optional<UserSession> optionalUserSession = userSessionRepository.findById(token);

        if(optionalUserSession.isEmpty()) {
            throw new AuthenticationException("Failed to authenticate the user.");
        }

        userSessionRepository.delete(optionalUserSession.get());
    }
}
