package com.indium.food_api.Controller;

import com.indium.food_api.entity.Donation;
import com.indium.food_api.entity.User;
import com.indium.food_api.service.DonationService;
import com.indium.food_api.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private NotificationService notificationService;

    // Create a new donation
    @PostMapping
    public Donation createDonation(@RequestBody Donation donation, @AuthenticationPrincipal User user) {
        Donation createdDonation = donationService.createDonation(donation, user);
        notificationService.notifyNGOsNearby(createdDonation);
        return createdDonation;
    }

    // Get available donations (unclaimed)
    @GetMapping
    public List<Donation> getAvailableDonations(@RequestParam(required = false) String city) {
        return donationService.getAvailableDonations(city);
    }

    // Claim a donation
    @PostMapping("/{id}/claim")
    public Donation claimDonation(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return donationService.claimDonation(id, user);
    }
}
