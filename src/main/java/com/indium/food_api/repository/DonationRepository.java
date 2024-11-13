package com.indium.food_api.repository;

import com.indium.food_api.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByClaimedFalse();

    List<Donation> findByAreaAndClaimedFalse(String area);
}

