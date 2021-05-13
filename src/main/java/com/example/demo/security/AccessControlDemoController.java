package com.example.demo.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AccessControlDemoController {

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello world");
    }

    @GetMapping("/api/stats")
    public ResponseEntity<?> stats() {
        return ResponseEntity.ok(Map.of(
                "counter", 20,
                "allowed", true
        ));
    }

    @GetMapping("/admin/watcher")
    public ResponseEntity<?> adminWatch() {
        return ResponseEntity.ok(Map.of(
                "view", "admin"
        ));
    }
}
