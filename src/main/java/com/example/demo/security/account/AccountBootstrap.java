package com.example.demo.security.account;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class AccountBootstrap {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener
    void on(ApplicationReadyEvent event) {
        Stream.of(
                new Account().setUsername("under").setPassword(passwordEncoder.encode("test")).setRoles(List.of("USER")),
                new Account().setUsername("admin").setPassword(passwordEncoder.encode("admintest")).setRoles(List.of("USER", "ADMIN"))
        ).forEach(accountRepository::save);
    }
}
