package com.example.demo.users.infra.web;

import com.example.demo.users.domain.User;
import com.example.demo.users.infra.web.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
class  UserAdapter {
    
    public UserResponse map(User user) {
        return new UserResponse()
                .setId(user.getId())
                .setUsername(user.getUsername());
    }
}