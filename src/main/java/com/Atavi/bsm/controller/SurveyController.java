package com.Atavi.bsm.controller;

import com.Atavi.bsm.requestDTO.BloodBankRequest;
import com.Atavi.bsm.requestDTO.SurveyRequest;
import com.Atavi.bsm.responseDTO.BloodBankResponse;
import com.Atavi.bsm.responseDTO.SurveyResponse;
import com.Atavi.bsm.service.BloodBankService;
import com.Atavi.bsm.service.SurveyService;
import com.Atavi.bsm.util.ResponseStructure;
import com.Atavi.bsm.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SurveyController {

    private final RestResponseBuilder responseBuilder;
    private final SurveyService surveyService;


    @PostMapping("/survey/{userId}")
    public ResponseEntity<ResponseStructure<SurveyResponse>> createSurvey(@RequestBody SurveyRequest surveyRequest, @PathVariable int userId)
    {
        SurveyResponse surveyResponse = surveyService.createSurvey(surveyRequest,userId);
        return responseBuilder.success(HttpStatus.CREATED, "Survey Response Added", surveyResponse);
    }

}
