package com.indium.food_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserType type; // Now an enum

    // Only required if type is "NGO"
    private String organization;
    private String area;
}
