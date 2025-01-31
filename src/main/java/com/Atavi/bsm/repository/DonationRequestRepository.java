package com.Atavi.bsm.repository;

import com.Atavi.bsm.entity.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRequestRepository extends JpaRepository<DonationRequest, Integer>
{
}
