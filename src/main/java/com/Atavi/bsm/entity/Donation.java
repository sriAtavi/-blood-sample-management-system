package com.Atavi.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donationId;
    private LocalDate donationDate;
    private LocalTime donationTime;

//    @ManyToOne
//    private User user;

//    @OneToMany(mappedBy = "donation")
//    private List<DonationRequest> donationRequests;

//    @OneToOne
//    private DonationLead donationLead;

}
