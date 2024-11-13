package com.indium.food_api.repository;

import com.indium.food_api.entity.User;
import com.indium.food_api.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username); // Add this
    Optional<User> findByPhone(String phone); // Add this
    List<User> findByType(UserType type); // Change parameter to UserType
}
