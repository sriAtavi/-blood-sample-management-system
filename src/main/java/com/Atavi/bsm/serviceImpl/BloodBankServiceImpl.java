package com.Atavi.bsm.serviceImpl;


import com.Atavi.bsm.entity.BloodBank;
import com.Atavi.bsm.exception.BloodBankNotFoundException;
import com.Atavi.bsm.repository.BloodBankRepository;
import com.Atavi.bsm.requestDTO.BloodBankRequest;

import com.Atavi.bsm.responseDTO.BloodBankResponse;

import com.Atavi.bsm.service.BloodBankService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BloodBankServiceImpl implements BloodBankService {

    private final BloodBankRepository bloodBankRepository;

    private BloodBank mapBloodBankToBloodBankRequest(BloodBankRequest bloodBankRequest, BloodBank bloodBank) {
        bloodBank.setBloodBankName(bloodBankRequest.getBloodBankName());
        bloodBank.setEmergencyUnitCount(bloodBankRequest.getEmergencyUnitCount());
        return bloodBank;
    }

    private BloodBankResponse mapBloodBankToBloodBankResponse(BloodBank bloodBank) {
        return BloodBankResponse.builder()
                .bloodBankId(bloodBank.getBloodBankId())
                .bloodBankName(bloodBank.getBloodBankName())
                .emergencyUnitCount(bloodBank.getEmergencyUnitCount())
                .build();
    }

    @Override
    public BloodBankResponse addBloodBank(BloodBankRequest bloodBankRequest) {
       BloodBank bloodBank = mapBloodBankToBloodBankRequest(bloodBankRequest,new BloodBank());
       bloodBankRepository.save(bloodBank);

       return mapBloodBankToBloodBankResponse(bloodBank);
    }

    @Override
    public BloodBankResponse findBloodBankById(int bloodBankId) {
        Optional<BloodBank> bloodBankResult = bloodBankRepository.findById(bloodBankId);

        if(bloodBankResult.isEmpty())
            throw new BloodBankNotFoundException("Blood Bank not found from the given ID");

        BloodBank bloodBank = bloodBankResult.get();

        return mapBloodBankToBloodBankResponse(bloodBank);
    }

    @Override
    public BloodBankResponse updateBloodBank(BloodBankRequest bloodBankRequest, int bloodBankId) {

        Optional<BloodBank> bloodBankResult = bloodBankRepository.findById(bloodBankId);

        if(bloodBankResult.isEmpty())
            throw new BloodBankNotFoundException("Blood Bank not found from the given ID");


        BloodBank existingBloodBank = bloodBankResult.get();
       // existingBloodBank = mapBloodBankToBloodBankRequest(bloodBankRequest,existingBloodBank);
        mapBloodBankToBloodBankRequest(bloodBankRequest,existingBloodBank);
        bloodBankRepository.save(existingBloodBank);

        return mapBloodBankToBloodBankResponse(existingBloodBank);
    }

    @Override
    public List<BloodBankResponse> findBloodBanks() {
       List<BloodBank> bloodBankList =  bloodBankRepository.findAll();
       List<BloodBankResponse> list = new ArrayList<>();

       for(BloodBank bloodBank : bloodBankList){
           BloodBankResponse bloodBankLst = BloodBankResponse.builder()
                   .bloodBankId(bloodBank.getBloodBankId())
                   .bloodBankName(bloodBank.getBloodBankName())
                   .emergencyUnitCount(bloodBank.getEmergencyUnitCount())
                   .build();

           list.add(bloodBankLst);
       }
       return list;
    }
}
