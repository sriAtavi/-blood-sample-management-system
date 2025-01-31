package com.Atavi.bsm.repository;

import com.Atavi.bsm.entity.BloodBank;
import com.Atavi.bsm.entity.BloodSample;
import com.Atavi.bsm.enums.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodSampleRepositiry extends JpaRepository<BloodSample, Integer> {

    List<BloodSample> findByBloodBankAndBloodGroupIn(BloodBank bloodBank,List<BloodGroup> bloodGroups);
}
