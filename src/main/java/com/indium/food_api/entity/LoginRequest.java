package com.indium.food_api.entity;

import lombok.Data;

@Data
public class LoginRequest {
    private String identifier;
    private String password;

    // Getters
    public String getIdentifier() {
        return identifier;
    }

    public String getPassword() {
        return password;
    }

    // Setters, if needed
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

