package com.example.demo.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class UserService {

    private final UserStore userStore;
    private final UserConfig userConfig;

    String addUser(String username) {
        if (userStore.count() + 1 > userConfig.getMax()) {
            throw new IllegalStateException("Cannot add more users");
        }

        var userId = userStore.store(username);
        System.out.println("Stored " + username);
        return userId;
    }

    List<User> findAll() {
        return userStore.findAll();
    }

    public Optional<User> findOne(String userId) {
        return userStore.findOne(userId);
    }
}
