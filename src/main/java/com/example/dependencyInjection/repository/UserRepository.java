package com.example.dependencyInjection.repository;

import com.example.dependencyInjection.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
