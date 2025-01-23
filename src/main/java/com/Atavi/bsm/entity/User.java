package com.Atavi.bsm.entity;

import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.enums.Gender;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String password;
    private String email;
    private Long phoneNumber;
    private LocalDate lastDonatedAt;
    private int age;
    private Gender gender;
    private String availableCity;
    private boolean verified;
    private BloodGroup bloodGroup;



}
