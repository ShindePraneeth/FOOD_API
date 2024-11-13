package com.indium.food_api.service;

import com.indium.food_api.entity.Donation;
import com.indium.food_api.entity.User;
import com.indium.food_api.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private NotificationService notificationService;

    public Donation createDonation(Donation donation, User donor) {
        donation.setDonor(donor);
        donation.setClaimed(false);
        return donationRepository.save(donation);
    }

    // Updated to use 'area' instead of 'city'
    public List<Donation> getAvailableDonations(String area) {
        if (area != null && !area.isEmpty()) {
            return donationRepository.findByAreaAndClaimedFalse(area);
        }
        return donationRepository.findByClaimedFalse();
    }

    public Donation claimDonation(Long donationId, User ngoUser) {
        Donation donation = donationRepository.findById(donationId).orElseThrow();
        donation.setClaimed(true);
        donation.setClaimedBy(ngoUser);
        donationRepository.save(donation);
        notificationService.notifyDonorOfClaim(donation, ngoUser);
        return donation;
    }
}
