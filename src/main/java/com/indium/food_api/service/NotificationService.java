package com.indium.food_api.service;

import com.indium.food_api.entity.Donation;
import com.indium.food_api.entity.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notifyNGOsNearby(Donation donation) {
        // Logic to notify nearby NGOs about the new donation
    }

    public void notifyDonorOfClaim(Donation donation, User ngoUser) {
        // Logic to send an email and/or SMS to the donor when their donation is claimed
    }
}
