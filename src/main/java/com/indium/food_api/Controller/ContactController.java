package com.indium.food_api.Controller;

import com.indium.food_api.entity.ContactMessage;
import com.indium.food_api.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<?> submitMessage(@RequestBody ContactMessage message) {
        return ResponseEntity.ok(contactService.saveMessage(message));
    }
}
