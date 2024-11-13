package com.indium.food_api.Controller;


import com.indium.food_api.entity.User;
import com.indium.food_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ngos")
public class NGOController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllNGOs() {
        return userService.getAllNGOs();
    }
}


