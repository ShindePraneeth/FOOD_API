package com.indium.food_api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodItem;
    private String quantity;
    private String location;
    private String area;
    private LocalDateTime expiryTime;

    // Default to false when a donation is created
    private Boolean claimed = false;

    // The donor who provided the donation
    @ManyToOne
    @JoinColumn(name = "donor_id", referencedColumnName = "id")
    private User donor;

    // The NGO or user who claimed the donation, if any
    @ManyToOne
    @JoinColumn(name = "claimed_by_id", referencedColumnName = "id")
    private User claimedBy;

    // Additional logic or methods (if required) can be added here
}
