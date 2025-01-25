package com.Atavi.bsm.entity;

import com.Atavi.bsm.enums.BloodGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloodSample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sampleId;
    private BloodGroup bloodGroup;
    private int quantity;
    private boolean availaility;
    private int emergencyUnits;
    private int availableUnits;
}
