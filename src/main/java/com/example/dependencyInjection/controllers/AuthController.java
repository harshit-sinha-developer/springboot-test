package com.example.dependencyInjection.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.dependencyInjection.model.LoginRequest;
import com.example.dependencyInjection.model.SignupRequest;
import com.example.dependencyInjection.model.User;
import com.example.dependencyInjection.model.UserSession;
import com.example.dependencyInjection.repository.UserRepository;
import com.example.dependencyInjection.repository.UserSessionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Value("${app.jwt_secret}")
    private String jwtSecret;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @PostMapping("/signup")
    public Boolean signup(@RequestBody SignupRequest signupRequest) {
        String encryptedPassword = BCrypt.hashpw(signupRequest.getPassword(), BCrypt.gensalt());

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(encryptedPassword);
        user.setName(signupRequest.getName());

        userRepository.save(user);

        return true;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

        if(optionalUser.isEmpty()) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            return "failure";
        }

        User user = optionalUser.get();

        if(BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {
//            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
//
//            return JWT.create()
//                    .withClaim("uId", user.getId())
//                    .withExpiresAt(new Date()) // 48 hours
//                    .sign(algorithm);

            UserSession userSession = new UserSession();
            userSession.setUserId(user.getId());
            userSession = userSessionRepository.save(userSession);
            return userSession.getId();
        }

        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        return "failure";
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String bearerToken = request.getHeader("Authorization");

        if(bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        String token = bearerToken.split(" ")[1];

        Optional<UserSession> optionalUserSession = userSessionRepository.findById(token);

        if(optionalUserSession.isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        userSessionRepository.delete(optionalUserSession.get());
    }
}
