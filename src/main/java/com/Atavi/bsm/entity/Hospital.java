package com.Atavi.bsm.entity;

import com.Atavi.bsm.requestDTO.DonatationRequestDto;
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
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hospitalId;
    private String hospitalName;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.EAGER)
    private List<Admin> admin;

    @OneToOne
    private Address address;


    @OneToMany//(fetch = FetchType.EAGER) // To avoid "org.springframework.orm.jpa.JpaSystemException => failed to lazily initialize a collection of role"
    private List<DonationRequest> donationRequest;
}
