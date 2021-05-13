package com.example.demo.security.account;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
class Account {
    private String username;
    private String password;
    private List<String> roles;
}