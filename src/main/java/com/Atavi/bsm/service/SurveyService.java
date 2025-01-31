package com.Atavi.bsm.service;

import com.Atavi.bsm.requestDTO.SurveyRequest;
import com.Atavi.bsm.responseDTO.SurveyResponse;

public interface SurveyService
{
    SurveyResponse createSurvey(SurveyRequest surveyRequest, int userId);
}
