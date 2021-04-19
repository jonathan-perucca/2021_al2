package com.example.demo.users.domain;

import com.example.demo.event.AggregateRoot;
import com.example.demo.users.domain.event.UserActivated;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User extends AggregateRoot {
    private String id;
    private String username;

    private boolean activated;

    public User activate() {
        this.activated = true;
        registerEvent(new UserActivated(id));
        return this;
    }
}
