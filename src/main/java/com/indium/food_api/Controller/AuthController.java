package com.indium.food_api.Controller;

import com.indium.food_api.JwtUtil;
import com.indium.food_api.entity.LoginRequest;
import com.indium.food_api.entity.LoginResponse;
import com.indium.food_api.entity.User;
import com.indium.food_api.entity.UserType;
import com.indium.food_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (user.getType() == UserType.NGO) {
            if (user.getOrganization() == null || user.getOrganization().isEmpty()) {
                return ResponseEntity.badRequest().body("Organization is required for NGO registration.");
            }
            if (user.getArea() == null || user.getArea().isEmpty()) {
                return ResponseEntity.badRequest().body("Service area is required for NGO registration.");
            }
        }


        try {
            User registeredUser = userService.registerUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Registration successful!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userService.findUserByIdentifier(request.getIdentifier());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getUsername()); // or user.getId() if designed to use ID
                LoginResponse response = new LoginResponse(true, "Login successful!", token, user.getUsername(), user.getType().name());
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(false, "Invalid credentials", null, null, null));
    }

}

