package com.example.demo.users.infra.web;

import com.example.demo.users.domain.UserService;
import com.example.demo.users.infra.web.exception.IllegalNameException;
import com.example.demo.users.infra.web.request.CreateUserRequest;
import com.example.demo.users.infra.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserAdapter userAdapter;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        var users = userService.findAll()
                .stream()
                .map(userAdapter::map)
                .collect(toList());

        return ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(
           @PathVariable("id") String userId
    ) {
        return userService.findOne(userId)
                .map(userAdapter::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }

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