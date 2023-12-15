package com.example.dependencyInjection.controllers;

import com.example.dependencyInjection.model.User;
import com.example.dependencyInjection.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"/user"})
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    private static Logger logger = LoggerFactory.getLogger("xxxxx");

    @GetMapping("")
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        for(User user: userRepository.findAll()) {
            users.add(user);
        }

        logger.info("Fetched users count: " + users.size());

        return users;
    }

    @GetMapping("/{userId}")
    public Optional<User> find(@PathVariable Long userId) {
        return userRepository.findById(userId);
    }
}
