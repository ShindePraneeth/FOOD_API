package com.indium.food_api.service;

import com.indium.food_api.entity.ContactMessage;
import com.indium.food_api.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private ContactMessageRepository contactMessageRepository;

    public ContactMessage saveMessage(ContactMessage message) {
        return contactMessageRepository.save(message);
    }
}
