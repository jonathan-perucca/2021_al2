package com.example.demo.users.infra.web.exception;

public class IllegalNameException extends RuntimeException {

    public IllegalNameException(String message) {
        super(message);
    }
}
