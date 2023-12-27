package com.example.dependencyInjection.repository;

import com.example.dependencyInjection.model.UserSession;
import org.springframework.data.repository.CrudRepository;

public interface UserSessionRepository extends CrudRepository<UserSession, String> {
}
