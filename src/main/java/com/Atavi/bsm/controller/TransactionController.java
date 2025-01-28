package com.Atavi.bsm.controller;

import com.Atavi.bsm.requestDTO.BloodBankRequest;
import com.Atavi.bsm.requestDTO.TransactionRequest;
import com.Atavi.bsm.responseDTO.BloodBankResponse;
import com.Atavi.bsm.responseDTO.TransactionResponse;
import com.Atavi.bsm.service.TransactionService;
import com.Atavi.bsm.util.ResponseStructure;
import com.Atavi.bsm.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final RestResponseBuilder responseBuilder;

    //@PreAuthorize("hasAuthority('OWNER_ADMIN')")
    @PostMapping("transaction/{bloodBankId}")
    public ResponseEntity<ResponseStructure<TransactionResponse>> createTransaction(@RequestBody TransactionRequest transactionRequest,@PathVariable int bloodBankId, @RequestParam int userId, @RequestParam int hospitalId)
    {
        TransactionResponse  transactionResponse = transactionService.createTransaction(transactionRequest,bloodBankId);
        return responseBuilder.success(HttpStatus.CREATED, "Transaction Created", transactionResponse);

    }
}
