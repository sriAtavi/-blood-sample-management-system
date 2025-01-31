package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.Hospital;
import com.Atavi.bsm.entity.Survey;
import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.exception.UserNotFoundException;
import com.Atavi.bsm.repository.SurveyRepository;
import com.Atavi.bsm.repository.UserRepository;
import com.Atavi.bsm.requestDTO.HospitalRequest;
import com.Atavi.bsm.requestDTO.SurveyRequest;
import com.Atavi.bsm.responseDTO.SurveyResponse;
import com.Atavi.bsm.responseDTO.UserResponse;
import com.Atavi.bsm.service.SurveyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class SurveyServiceImpl implements SurveyService
{
    SurveyRepository surveyRepository;
    UserRepository userRepository;

    private Survey mapSUrveyTOSUrveyRequest(SurveyRequest surveyRequest, Survey survey) {
        survey.setAnyAdditives(surveyRequest.isAnyAdditives());
        survey.setAnyMedication(surveyRequest.isAnyMedication());
        survey.setConsumedAlcohol(surveyRequest.isConsumedAlcohol());
        survey.setConsumedTobacco(surveyRequest.isConsumedTobacco());
        survey.setPremedicalCondition(surveyRequest.isPremedicalCondition());
        return survey;
    }

    private SurveyResponse mapToSurveyrResponse(Survey survey)
    {
        return SurveyResponse.builder()
                .surveyId(survey.getSurveyId())
                .anyAdditives(survey.isAnyAdditives())
                .anyMedication(survey.isAnyMedication())
                .consumedAlcohol(survey.isConsumedAlcohol())
                .consumedTobacco(survey.isConsumedTobacco())
                .premedicalCondition(survey.isPremedicalCondition())
                .build();
    }

    @Override
    public SurveyResponse createSurvey(SurveyRequest surveyRequest, int userId)
    {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new UserNotFoundException("User not found");

        User userDetails = user.get();

        Survey survey = mapSUrveyTOSUrveyRequest(surveyRequest,new Survey());
        survey.setUser(userDetails);

        surveyRepository.save(survey);

        return mapToSurveyrResponse(survey);


    }
}
