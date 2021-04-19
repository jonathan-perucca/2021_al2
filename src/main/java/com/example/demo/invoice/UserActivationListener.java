package com.example.demo.invoice;

import com.example.demo.users.domain.event.UserActivated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserActivationListener {

    @Async
    @EventListener
    void on(UserActivated event) {
        log.info("{} just activated !", event.getId());
    }
}
