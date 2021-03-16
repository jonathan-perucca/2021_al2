package com.example.demo.users;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserBoostrap {

    private final UserService userService;

    @EventListener
    void on(ApplicationReadyEvent event) {
        for (int i = 0; i < 2; i++) {
            userService.addUser("Bootstraped-"+i);
        }
    }
}
