package com.indium.food_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private boolean success;
    private String message;
    private String token;
    private String username;
    private String userType;

    // Constructor, getters, and setters
}

