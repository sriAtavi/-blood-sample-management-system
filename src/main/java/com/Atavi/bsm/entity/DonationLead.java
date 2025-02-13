package com.Atavi.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationLead
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leadId;
    private LocalDate leadDate;
    private LocalTime leadTime;

//    @ManyToOne
//    private User user;
//
//    @OneToMany
//    private List<DonationRequest> donationRequest;
//
//    @OneToOne
//    private Donation donation;
}
