package com.Atavi.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int surveyId;
    private boolean premedicalCondition;
    private boolean consumedAlcohol;
    private boolean consumedTobacco;
    private boolean anyAdditives;
    private boolean anyMedication;

    @ManyToOne
    private User user;
}
