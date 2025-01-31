package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.DonationRequest;
import com.Atavi.bsm.entity.Hospital;
import com.Atavi.bsm.exception.HospitalNotFoundException;
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
    HospitalRepository hospitalRepository;

    @Override
    public DonationRequestResponse createDonationRequestForHospital(DonatationRequestDto donationRequest, int hospitalId)
    {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(hospitalId);
        if(optionalHospital.isEmpty())
            throw new HospitalNotFoundException("Hospital nnt found");

        Hospital hospital = optionalHospital.get();

        DonationRequest newRequest = mapToDonationRequest(donationRequest,new DonationRequest());

        List<DonationRequest> requestList = new ArrayList<>();
        requestList.add(newRequest);
        hospital.setDonationRequest(requestList); // Updating DonationRequest Details in Hospital


        donationRequestRepository.save(newRequest);

        hospitalRepository.save(hospital);
        return mapTODonationResponse(newRequest);
    }

    private DonationRequestResponse mapTODonationResponse(DonationRequest donationRequest)
    {
        return DonationRequestResponse.builder()
                .donationId(donationRequest.getDonationId())
                .date(donationRequest.getDate())
                .time(donationRequest.getTime())
                .bloodGroup(donationRequest.getBloodGroup())
                .build();
    }

    private DonationRequest mapToDonationRequest(DonatationRequestDto clientRequest, DonationRequest donationRequest)
    {
        clientRequest.setDate(donationRequest.getDate());
        donationRequest.setTime(clientRequest.getTime());
        donationRequest.setBloodGroup(donationRequest.getBloodGroup());

        return donationRequest;
    }
}
