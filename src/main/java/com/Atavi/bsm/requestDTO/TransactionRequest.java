package com.Atavi.bsm.requestDTO;

import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest
{
    private int transactionId;

    private TransactionType transactionType;
    private LocalDate transactionDate;
    private int noOfUnitsTransacted;
    private BloodGroup bloodGroup;
}
