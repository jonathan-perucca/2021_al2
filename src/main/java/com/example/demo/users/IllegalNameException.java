package com.example.demo.users;

public class IllegalNameException extends RuntimeException {

    public IllegalNameException(String message) {
        super(message);
    }
}
