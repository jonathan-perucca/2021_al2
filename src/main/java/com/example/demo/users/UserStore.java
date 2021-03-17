package com.example.demo.users;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
class UserStore {
    private final Map<String, User> userDb = new HashMap<>();

    public String store(String username) {
        String userId = UUID.randomUUID().toString();
        userDb.put(userId, new User(userId, username));
        return userId;
    }

    public int count() {
        return userDb.size();
    }

    public List<User> findAll() {
        return new ArrayList<>(userDb.values());
    }

    public Optional<User> findOne(String userId) {
        return Optional.ofNullable(userDb.get(userId));
    }
}
