package com.Atavi.bsm.entity;

import com.Atavi.bsm.enums.BloodGroup;
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
public class DonationRequest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donationId;
    private LocalDate donationRequestDate;
    private LocalTime donationRequestTime;

    private List<BloodGroup> bloodGroup;



}
