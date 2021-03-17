package com.example.demo.users;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request,
                                        HttpServletRequest initRequest) {
        String userId = userService.addUser(request.getUsername());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


}

