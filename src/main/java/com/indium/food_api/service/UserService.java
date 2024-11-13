package com.indium.food_api.service;

import com.indium.food_api.entity.User;
import com.indium.food_api.entity.UserType; // Import the UserType enum
import com.indium.food_api.repository.UserRepository;
import com.indium.food_api.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User registerUser(User user) {
        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllNGOs() {
        return userRepository.findByType(UserType.NGO); // This should now work
    }

    public Optional<User> findUserByIdentifier(String identifier) {
        return userRepository.findByEmail(identifier)
                .or(() -> userRepository.findByUsername(identifier))
                .or(() -> userRepository.findByPhone(identifier));
    }

    public Map<String, Object> login(String identifier, String password) {
        Optional<User> userOpt = userRepository.findByEmail(identifier)
                .or(() -> userRepository.findByUsername(identifier))
                .or(() -> userRepository.findByPhone(identifier));

        Map<String, Object> response = new HashMap<>();
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            User user = userOpt.get();
            String token = jwtUtil.generateToken(user.getUsername());

            response.put("success", true);
            response.put("message", "Login successful!");
            response.put("token", token);
            response.put("userType", user.getType());
            response.put("username", user.getUsername());
        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials.");
        }
        return response;
    }
}
