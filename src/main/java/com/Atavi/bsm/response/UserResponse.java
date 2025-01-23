package com.Atavi.bsm.response;

import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.enums.Gender;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class UserResponse
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;



    private LocalDate lastDonatedAt;

    private Gender gender;
    private String availableCity;
    private boolean verified;
    private BloodGroup bloodGroup;
}
