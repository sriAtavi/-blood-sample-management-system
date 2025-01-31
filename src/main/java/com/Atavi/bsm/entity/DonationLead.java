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
}
