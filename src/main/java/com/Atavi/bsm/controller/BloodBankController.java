package com.Atavi.bsm.controller;

import com.Atavi.bsm.requestDTO.BloodBankRequest;
import com.Atavi.bsm.responseDTO.BloodBankResponse;
import com.Atavi.bsm.service.BloodBankService;
import com.Atavi.bsm.util.ResponseStructure;
import com.Atavi.bsm.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BloodBankController {

    private final BloodBankService bloodBankService;
    private final RestResponseBuilder responseBuilder;
    @PostMapping("/blood-banks")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> addBloodBank(@RequestBody BloodBankRequest bloodBankRequest)
    {
        BloodBankResponse  bloodBankResponse = bloodBankService.addBloodBank(bloodBankRequest);
        return responseBuilder.success(HttpStatus.CREATED, "Blood Bank Created", bloodBankResponse);
    }

    @GetMapping("/blood-banks/{bloodBankId}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> findBloodBankById(@PathVariable int bloodBankId)
    {
        BloodBankResponse  bloodBankResponse = bloodBankService.findBloodBankById(bloodBankId);
        return responseBuilder.success(HttpStatus.FOUND, "Blood Bank Found", bloodBankResponse);
    }
    @PutMapping("/blood-Banks/{bloodBankId}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> updateBloodBank(@RequestBody BloodBankRequest bloodBankRequest,@PathVariable int bloodBankId)
    {
        BloodBankResponse  bloodBankResponse = bloodBankService.updateBloodBank(bloodBankRequest,bloodBankId);
        return responseBuilder.success(HttpStatus.CREATED, "Blood Bank Updated", bloodBankResponse);
    }

    @GetMapping("/blood-Banks")
    public ResponseEntity<ResponseStructure<List<BloodBankResponse>>> findAllBloodBank()
    {
        List<BloodBankResponse>  bloodBankResponse = bloodBankService.findBloodBanks();
        return responseBuilder.success(HttpStatus.FOUND, "Blood Banks List", bloodBankResponse);
    }
}
