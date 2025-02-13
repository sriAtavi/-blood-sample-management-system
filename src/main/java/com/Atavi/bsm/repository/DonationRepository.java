package com.Atavi.bsm.repository;

import com.Atavi.bsm.entity.Donation;
import com.Atavi.bsm.entity.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {

}
