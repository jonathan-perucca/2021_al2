package com.example.demo.users.infra.repository;

import com.example.demo.users.domain.User;
import com.example.demo.users.domain.UserRepository;

import java.util.*;

//@Repository
class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> userDb = new HashMap<>();

    @Override
    public String store(String username) {
        String userId = UUID.randomUUID().toString();
        userDb.put(userId, new User().setId(userId).setUsername(username));
        return userId;
    }

    @Override
    public int count() {
        return userDb.size();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userDb.values());
    }

    @Override
    public Optional<User> findOne(String userId) {
        return Optional.ofNullable(userDb.get(userId));
    }

    @Override
    public void save(User user) {

    }
}
