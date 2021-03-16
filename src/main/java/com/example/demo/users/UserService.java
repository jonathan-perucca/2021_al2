package com.example.demo.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class UserService {

    private final UserStore userStore;
    private final UserConfig userConfig;

    void addUser(String username) {
        if (userStore.count() + 1 > userConfig.getMax()) {
            throw new IllegalStateException("Cannot add more users");
        }

        userStore.store(username);
        System.out.println("Stored " + username);
    }

    List<String> findAll() {
        return userStore.findAll();
    }
}
