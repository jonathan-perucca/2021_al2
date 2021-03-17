package com.example.demo.users;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ok(userService.findAll());
    }

    // une api GET /api/users/{id} pour récupérer le user
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(
           @PathVariable("id") String userId
    ) {
        return userService.findOne(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }

    // une api POST { "username": "Smith" } pour créer un user
    // si on a réussi a créer le user
    // alors réponse 201 avec header
    // Location : <url pour l'api de récupération du user>
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        if ("none".equalsIgnoreCase(request.getUsername())) {
            throw new IllegalNameException("Illegal name for none");
        }

        String userId = userService.addUser(request.getUsername());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
