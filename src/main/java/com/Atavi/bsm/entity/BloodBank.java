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

    @OneToMany
    private List<Admin> admin;

    @OneToMany
    private List<BloodSample> bloodSample;

    @OneToOne
    private Address address;
}
