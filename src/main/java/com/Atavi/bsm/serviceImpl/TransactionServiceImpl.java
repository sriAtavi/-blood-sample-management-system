package com.Atavi.bsm.serviceImpl;

import com.Atavi.bsm.entity.BloodBank;
import com.Atavi.bsm.entity.BloodSample;
import com.Atavi.bsm.entity.Transaction;
import com.Atavi.bsm.enums.TransactionType;
import com.Atavi.bsm.repository.BloodBankRepository;
import com.Atavi.bsm.repository.BloodSampleRepositiry;
import com.Atavi.bsm.repository.TransactionRepository;
import com.Atavi.bsm.requestDTO.BloodSampleRequest;
import com.Atavi.bsm.requestDTO.TransactionRequest;
import com.Atavi.bsm.responseDTO.BloodSampleResponse;
import com.Atavi.bsm.responseDTO.TransactionResponse;
import com.Atavi.bsm.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BloodBankRepository bloodBankRepository;
    private final BloodSampleRepositiry bloodSampleRepositiry;

    public Transaction mapTransactionToTransactionRequest(TransactionRequest transactionRequest, Transaction transaction) {
        transaction.setTransactionDate(transactionRequest.getTransactionDate());
        transaction.setBloodGroup(transactionRequest.getBloodGroup());
        transaction.setNoOfUnitsTransacted(transactionRequest.getNoOfUnitsTransacted());
        transaction.setTransactionType(transactionRequest.getTransactionType());
        return transaction;
    }

    public TransactionResponse mapTransactionToTransactionSampleResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .transactionType(transaction.getTransactionType())
                .noOfUnitsTransacted(transaction.getNoOfUnitsTransacted())
                .transactionDate(transaction.getTransactionDate())
                .bloodGroup(transaction.getBloodGroup())
                .build();

    }

    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest, int bloodBankId)
    {

        Transaction transaction = null;

        Optional<BloodBank> currentBank = bloodBankRepository.findById(bloodBankId);


        BloodBank bloodBank = currentBank.get();

        List<BloodSample> bloodSample = bloodBank.getBloodSample();

        for (BloodSample sample : bloodSample)
        {
            int availableUnits = sample.getAvailableUnits();
            //  System.out.println(availableUnits);

            if (transactionRequest.getTransactionType() == TransactionType.NORMAL)
            {
                if (transactionRequest.getNoOfUnitsTransacted() < availableUnits)
                {
                    transaction = mapTransactionToTransactionRequest(transactionRequest, new Transaction());


                    sample.setAvailableUnits(availableUnits - transactionRequest.getNoOfUnitsTransacted());
                    sample.setQuantity(sample.getQuantity() - transactionRequest.getNoOfUnitsTransacted());


                    bloodSampleRepositiry.save(sample);
                    transactionRepository.save(transaction);

                } else
                {
                    throw new RuntimeException("Transaction Declined");
                }
            }
            else if (transactionRequest.getTransactionType() == TransactionType.EMERGENCY)
            {
                {
                    if (transactionRequest.getNoOfUnitsTransacted() < availableUnits)
                    {
                        transaction = mapTransactionToTransactionRequest(transactionRequest, new Transaction());


                        sample.setAvailableUnits(availableUnits - transactionRequest.getNoOfUnitsTransacted());
                        sample.setQuantity(sample.getQuantity() - transactionRequest.getNoOfUnitsTransacted());


                        bloodSampleRepositiry.save(sample);
                        transactionRepository.save(transaction);

                    }
                    else if (transactionRequest.getNoOfUnitsTransacted() > availableUnits && transactionRequest.getNoOfUnitsTransacted() < sample.getEmergencyUnits())
                    {
//                        if (transactionRequest.getNoOfUnitsTransacted() < sample.getEmergencyUnits())
//                        {
                            transaction = mapTransactionToTransactionRequest(transactionRequest, new Transaction());
                            sample.setEmergencyUnits(sample.getEmergencyUnits() - transactionRequest.getNoOfUnitsTransacted());
                            sample.setQuantity(sample.getQuantity() - transactionRequest.getNoOfUnitsTransacted());
                            bloodSampleRepositiry.save(sample);
                            transactionRepository.save(transaction);
//                        }


                    }
                    else {
                        throw new RuntimeException("Transaction Declained");
                    }

                }
            }
        }
        return mapTransactionToTransactionSampleResponse(transaction);
    }
}