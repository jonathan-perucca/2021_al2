package com.example.demo.users.domain;

import com.example.demo.users.UserConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConfig userConfig;

    public String addUser(String username) {
        if (userRepository.count() + 1 > userConfig.getMax()) {
            throw new IllegalStateException("Cannot add more users");
        }

        var userId = userRepository.store(username);
        log.info("Stored {}", username);
        return userId;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(String userId) {
        return userRepository.findOne(userId);
    }
}
