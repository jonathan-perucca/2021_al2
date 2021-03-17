package com.example.demo.users;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
class ErrorResponse {
    private int code;
    private String message;
}