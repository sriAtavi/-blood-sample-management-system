package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.BloodBank;
import com.Atavi.bsm.entity.BloodSample;
import com.Atavi.bsm.repository.BloodBankRepository;
import com.Atavi.bsm.repository.BloodSampleRepositiry;
import com.Atavi.bsm.requestDTO.BloodSampleRequest;
import com.Atavi.bsm.responseDTO.BloodSampleResponse;
import com.Atavi.bsm.service.BloodSampleService;
import com.Atavi.bsm.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Sample implements BloodSampleService {

    private final BloodSampleRepositiry bloodSampleRepositiry;
    private final RestResponseBuilder restResponseBuilder;
    private final BloodBankRepository bloodBankRepository;

    public BloodSample mapSampleToBloodSampleRequest(BloodSampleRequest sampleRequest,BloodSample sample) {
       sample.setBloodGroup(sampleRequest.getBloodGroup());
       //sample.setAvailability(sampleRequest.isAvailability());
       sample.setQuantity(sampleRequest.getQuantity());

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
    public BloodSampleResponse addBloodSample(BloodSampleRequest sampleRequest, int bloodBankId) {

        Optional<BloodBank> bloodBank = bloodBankRepository.findById(bloodBankId);

        if(bloodBank.isEmpty())
        throw new RuntimeException("blood bank not found");
        BloodBank bloodBankDetails = bloodBank.get();


        BloodSample newSample = mapSampleToBloodSampleRequest(sampleRequest, new BloodSample());
        List<BloodSample> bloodSampleList = new ArrayList<>();


        newSample.setAvailableUnits(sampleRequest.getQuantity() - bloodBankDetails.getEmergencyUnitCount());
        newSample.setEmergencyUnits(bloodBankDetails.getEmergencyUnitCount());
        bloodSampleList.add(newSample);
        bloodBankDetails.setBloodSample(bloodSampleList);


        bloodSampleRepositiry.save(newSample);
        bloodSampleRepositiry.save(newSample);

        return mapSampleToBloodSampleResponse(newSample);
    }
}
