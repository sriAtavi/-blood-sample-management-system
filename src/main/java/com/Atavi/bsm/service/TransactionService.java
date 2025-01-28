package com.Atavi.bsm.service;

import com.Atavi.bsm.requestDTO.TransactionRequest;
import com.Atavi.bsm.responseDTO.TransactionResponse;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest transactionRequest, int bloodBankId);
}
