package com.Atavi.bsm.controller;

import com.Atavi.bsm.entity.BloodBank;
import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.requestDTO.BloodBankRequest;
import com.Atavi.bsm.requestDTO.HospitalRequest;
import com.Atavi.bsm.responseDTO.BloodBankPageResponse;
import com.Atavi.bsm.responseDTO.BloodBankResponse;
import com.Atavi.bsm.responseDTO.HospitalResponse;
import com.Atavi.bsm.service.BloodBankService;
import com.Atavi.bsm.util.PageStructure;
import com.Atavi.bsm.util.ResponseStructure;
import com.Atavi.bsm.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BloodBankController {

    private final BloodBankService bloodBankService;
    private final RestResponseBuilder responseBuilder;
    private final List<String> forConstructorPurpose;


    @PostMapping("/blood-banks/{adminId}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> addBloodBankWithAdmin(@RequestBody BloodBankRequest bloodBankRequest,@PathVariable int adminId)
    {
        BloodBankResponse  bloodBankResponse = bloodBankService.addBloodBankWithAdmin(bloodBankRequest,adminId);
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

//    @GetMapping("/blood-Banks")
//    //public ResponseEntity<ResponseStructure<List<BloodBankResponse>>> findAllBloodBank()
//    public ResponseEntity<ResponseStructure<List<BloodBankPageResponse>>> findAllBloodBank(@RequestParam List<String> city, @RequestParam BloodGroup bloodGroup, @RequestParam int pageNumber, @RequestParam int pageSize)
//    {
//        List<BloodBankPageResponse>  bloodBankPageResponse = bloodBankService.findAllBloodBanks(city,bloodGroup,pageNumber,pageSize); // Updating Page Parameters
//        return responseBuilder.success(HttpStatus.FOUND, "Blood Banks List", bloodBankPageResponse);
//    }

    @GetMapping("/blood-Banks")
    //public ResponseEntity<ResponseStructure<List<BloodBankResponse>>> findAllBloodBank()
    public ResponseEntity<PageStructure<List<BloodBankPageResponse>>> findAllBloodBank(@RequestParam List<String> city, @RequestParam BloodGroup bloodGroup, @RequestParam int pageNumber, @RequestParam int pageSize)
    {
        Page<BloodBank> bloodBankPageResponse = bloodBankService.findAllBloodBanks(city,bloodGroup,pageNumber,pageSize); // Updating Page Parameters
        List<BloodBankPageResponse> pages = bloodBankService.generateBloodBankPageResponse(bloodBankPageResponse,bloodGroup);

        return responseBuilder.success
                (HttpStatus.FOUND,
                        "Blood Banks Pages",
                        pages,pageNumber,30,pageSize);
    }








}
