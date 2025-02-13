package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.BloodBank;
import com.Atavi.bsm.entity.DonationRequest;
import com.Atavi.bsm.entity.Hospital;
import com.Atavi.bsm.enums.OrganizationType;
import com.Atavi.bsm.exception.BloodBankNotFoundException;
import com.Atavi.bsm.exception.HospitalNotFoundException;
import com.Atavi.bsm.repository.BloodBankRepository;
import com.Atavi.bsm.repository.DonationRequestRepository;
import com.Atavi.bsm.repository.HospitalRepository;
import com.Atavi.bsm.requestDTO.DonatationRequestDto;
import com.Atavi.bsm.responseDTO.DonationRequestResponse;
import com.Atavi.bsm.service.DonationRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DonationRequestServiceImpl implements DonationRequestService {

    private final DonationRequestRepository donationRequestRepository;
    private final HospitalRepository hospitalRepository;
    private final BloodBankRepository bloodBankRepository;

    @Override
    public DonationRequestResponse createDonationRequestForHospital(DonatationRequestDto donationRequest, int hospitalId)
    {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(hospitalId);
        if(optionalHospital.isEmpty())
            throw new HospitalNotFoundException("Hospital not found");

        Hospital hospital = optionalHospital.get();

        DonationRequest newRequest = mapToDonationRequest(donationRequest,new DonationRequest());


        List<DonationRequest> requestList = new ArrayList<>();


        newRequest.setOrganizationType(OrganizationType.HOSPITAL); // This Donation Request is Done By Hospital
        requestList.add(newRequest);
        hospital.setDonationRequest(requestList); // Updating DonationRequest Details in Hospital


        donationRequestRepository.save(newRequest);

        hospitalRepository.save(hospital);
        return mapTODonationResponse(newRequest);
    }

    @Override
    public DonationRequestResponse createDonationRequestFromBloodBank(DonatationRequestDto donationRequest, int bloodBankId) {

        Optional<BloodBank> optionalBloodBank = bloodBankRepository.findById(bloodBankId);
        if(optionalBloodBank.isEmpty())
            throw new BloodBankNotFoundException("Blood Bank not found");

        BloodBank bloodBank = optionalBloodBank.get();

        DonationRequest newRequest = mapToDonationRequest(donationRequest,new DonationRequest());

        List<DonationRequest> requestList = new ArrayList<>();
        newRequest.setOrganizationType(OrganizationType.BLOOD_BANK); // This Donation Request is Done By Blood Bank
        requestList.add(newRequest);
        bloodBank.setDonationRequest(requestList); // Updating DonationRequest Details in BloodBank


        donationRequestRepository.save(newRequest);

        bloodBankRepository.save(bloodBank);
        return mapTODonationResponse(newRequest);
    }

    private DonationRequestResponse mapTODonationResponse(DonationRequest donationRequest)
    {
        return DonationRequestResponse.builder()
                .donationId(donationRequest.getDonationId())
                .donationRequestDate(donationRequest.getDonationRequestDate())
                .donationRequestTime(donationRequest.getDonationRequestTime())
                .bloodGroup(donationRequest.getBloodGroup())
                .cities(donationRequest.getCities())
                .organizationType(donationRequest.getOrganizationType())
                .build();



    }

    private DonationRequest mapToDonationRequest(DonatationRequestDto clientRequest, DonationRequest donationRequest)
    {
        donationRequest.setDonationRequestDate(clientRequest.getDonationRequestDate());
        donationRequest.setDonationRequestTime(clientRequest.getDonationRequestTime());
        donationRequest.setBloodGroup(clientRequest.getBloodGroup());
        donationRequest.setCities(clientRequest.getCities());

        return donationRequest;
    }
}
