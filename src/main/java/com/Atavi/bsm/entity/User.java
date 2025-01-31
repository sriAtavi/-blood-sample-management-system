package com.Atavi.bsm.entity;

import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.enums.Gender;
import com.Atavi.bsm.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;



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
    private UserRole userRole;

    @OneToOne(mappedBy = "user",fetch = FetchType.EAGER) // This denotes that Admin is the owning Side here
    private Admin admin;


    @OneToOne
    private Address address;

    //30-01-2025
    @OneToMany(mappedBy = "user") // Survey Table will have 'User' Foreign Key
    private List<Survey> survey;

}
