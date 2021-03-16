package com.example.demo.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<String>> getAllUsers() {
        return ok(userService.findAll());
    }

    // une api GET /users/{id} pour récupérer le user


    // une api POST pour créer un user
    // si on a réussi a créer le user
    // alors réponse 201 avec header Location : <url pour l'api de récupération du user>

}

