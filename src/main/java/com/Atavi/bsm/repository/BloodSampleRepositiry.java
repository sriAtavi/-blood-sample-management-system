package com.Atavi.bsm.repository;

import com.Atavi.bsm.entity.BloodSample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodSampleRepositiry extends JpaRepository<BloodSample, Integer> {
}
