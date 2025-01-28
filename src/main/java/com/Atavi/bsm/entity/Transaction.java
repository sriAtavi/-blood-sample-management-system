package com.Atavi.bsm.entity;

import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    private TransactionType transactionType;
    private LocalDate transactionDate;
    private int noOfUnitsTransacted;
    private BloodGroup bloodGroup;




}
