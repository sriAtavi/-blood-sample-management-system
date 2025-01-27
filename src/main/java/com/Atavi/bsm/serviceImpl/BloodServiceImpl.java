package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.BloodSample;
import com.Atavi.bsm.repository.BloodSampleRepositiry;
import com.Atavi.bsm.requestDTO.BloodSampleRequest;
import com.Atavi.bsm.responseDTO.BloodSampleResponse;
import com.Atavi.bsm.service.BloodSampleService;
import com.Atavi.bsm.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BloodServiceImpl implements BloodSampleService {

    BloodSampleRepositiry bloodSampleRepositiry;
    RestResponseBuilder restResponseBuilder;

    public BloodSample mapSampleToBloodSampleRequest(BloodSampleRequest sampleRequest,BloodSample sample) {
       sample.setBloodGroup(sampleRequest.getBloodGroup());
       sample.setAvailability(sampleRequest.isAvailability());
       sample.setQuantity(sampleRequest.getQuantity());
       sample.setAvailableUnits(sampleRequest.getAvailableUnits());
       sample.setEmergencyUnits(sampleRequest.getEmergencyUnits());
       return sample;
    }

    public BloodSampleResponse mapSampleToBloodSampleResponse(BloodSample sample) {
        return BloodSampleResponse.builder()
                .sampleId(sample.getSampleId())
                .bloodGroup(sample.getBloodGroup())
                .quantity(sample.getQuantity())
                .availability(sample.isAvailability())
                .availableUnits(sample.getAvailableUnits())
                .emergencyUnits(sample.getEmergencyUnits())
                .build();
    }

    @Override
    public BloodSampleResponse addBloodSample(BloodSampleRequest sampleRequest) {

        BloodSample newSample = mapSampleToBloodSampleRequest(sampleRequest, new BloodSample());
        bloodSampleRepositiry.save(newSample);

        return mapSampleToBloodSampleResponse(newSample);
    }
}
