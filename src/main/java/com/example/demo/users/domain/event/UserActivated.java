package com.example.demo.users.domain.event;

import com.example.demo.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserActivated implements DomainEvent {
    private String id;
}