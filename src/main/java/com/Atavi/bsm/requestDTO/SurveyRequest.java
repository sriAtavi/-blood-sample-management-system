package com.Atavi.bsm.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyRequest {

    private boolean premedicalCondition;
    private boolean consumedAlcohol;
    private boolean consumedTobacco;
    private boolean anyAdditives;
    private boolean anyMedication;
}
