package com.Atavi.bsm.controller;

import com.Atavi.bsm.requestDTO.HospitalRequest;
import com.Atavi.bsm.requestDTO.UserRequest;
import com.Atavi.bsm.responseDTO.HospitalResponse;
import com.Atavi.bsm.responseDTO.UserResponse;
import com.Atavi.bsm.service.HospitalService;
import com.Atavi.bsm.util.ResponseStructure;
import com.Atavi.bsm.util.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final RestResponseBuilder responseBuilder;

//        @PostMapping("/add-hospital")
//    public ResponseEntity<ResponseStructure<HospitalResponse>> addHospital(@RequestBody HospitalRequest hospitalRequest)
//    {
//        HospitalResponse  hospitalResponse = hospitalService.addHospital(hospitalRequest);
//        return responseBuilder.success(HttpStatus.CREATED, "Hospital Created", hospitalResponse);
//    }

    @GetMapping("/hospitalss/{hospitalId}")
    public ResponseEntity<ResponseStructure<HospitalResponse>> findByHospitalId(@PathVariable int hospitalId)
    {
        HospitalResponse   hospitalResponse  = hospitalService.findByHospitalId(hospitalId);
        return responseBuilder.success(HttpStatus.FOUND, "Hospital Found", hospitalResponse);
    }
    @PutMapping("/hospitalss/{hospitalId}")
    public ResponseEntity<ResponseStructure<HospitalResponse>> updateHospital(@RequestBody HospitalRequest  hospitalRequest,@PathVariable int hospitalId)
    {
        HospitalResponse  hospitalResponse = hospitalService.updateHospital(hospitalRequest,hospitalId);
        return responseBuilder.success(HttpStatus.CREATED, "Hospital Updated", hospitalResponse);
    }


//    @PostMapping("/addHospitalAdmin/{userId}")
//    public ResponseEntity<ResponseStructure<HospitalResponse>> addHospitalAdmin(@RequestBody HospitalRequest hospitalRequest,@PathVariable int userId)
//    {
//        HospitalResponse  hospitalResponse = hospitalService.addHospitalAdmin(hospitalRequest,userId);
//        return responseBuilder.success(HttpStatus.CREATED, "Hospital Created", hospitalResponse);
//    }


    @PostMapping("/hospital/{adminId}")
    public ResponseEntity<ResponseStructure<HospitalResponse>> addHospitalAdmin(@RequestBody HospitalRequest hospitalRequest,@PathVariable int adminId)
    {
        HospitalResponse  hospitalResponse = hospitalService.addHospitalAdmin(hospitalRequest,adminId);
        return responseBuilder.success(HttpStatus.CREATED, "Hospital Admin Created", hospitalResponse);
    }
}
