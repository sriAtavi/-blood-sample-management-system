package com.Atavi.bsm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Donation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donationId;
    private LocalDate donationDate;
    private LocalTime donationTime;
}
