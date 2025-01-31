package com.Atavi.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BloodBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bloodBankId;
    private String bloodBankName;
    private int emergencyUnitCount;

    @OneToMany(mappedBy = "bloodBank", fetch = FetchType.EAGER) // mappedBy creates bloodBankId foreign key column inAdmin Table which restricts Extra Table having 'BloodBank-Admin' relation
    private List<Admin> admin;

    @OneToMany(mappedBy = "bloodBank")
    private List<BloodSample> bloodSample;

    @OneToOne
    private Address address;

    @OneToMany
    private List<DonationRequest> donationRequest;
}
