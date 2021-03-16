package com.example.demo.users;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
class UserStore {
    private final List<String> userDb = new ArrayList<>();

    public void store(String username) {
        userDb.add(username);
    }

    public int count() {
        return userDb.size();
    }

    public List<String> findAll() {
        return new ArrayList<>(userDb);
    }

}
