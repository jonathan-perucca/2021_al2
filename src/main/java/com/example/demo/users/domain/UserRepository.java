package com.example.demo.users.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    String store(String username);
    int count();
    List<User> findAll();
    Optional<User> findOne(String userId);
    void save(User user);
}
