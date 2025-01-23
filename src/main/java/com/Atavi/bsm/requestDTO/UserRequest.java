package com.Atavi.bsm.requestDTO;

import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.enums.Gender;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor // This is used to serialize
public class UserRequest //While giving request from user
{ //DTO=> Data Transfer Object ( What all the objects to be transferred)
    private String userName;
    private String password;
    private String email;
    private Long phoneNumber;
    private int age;
    private Gender gender;
    private String availableCity;
    private BloodGroup bloodGroup;
    private LocalDate lastDonatedAt;
}
