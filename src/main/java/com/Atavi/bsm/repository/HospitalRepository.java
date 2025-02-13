package com.Atavi.bsm.repository;

import com.Atavi.bsm.entity.DonationRequest;
import com.Atavi.bsm.entity.Hospital;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    Optional<Hospital> findByDonationRequest(DonationRequest donationRequest);
}
