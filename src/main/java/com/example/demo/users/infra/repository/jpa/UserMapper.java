package com.example.demo.users.infra.repository.jpa;

import com.example.demo.users.domain.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {
    User map(UserEntity entity) {
        return new User()
                .setId(entity.getId())
                .setUsername(entity.getUsername());
    }

    public UserEntity map(User user) {
        return new UserEntity()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setActivated(user.isActivated());
    }
}