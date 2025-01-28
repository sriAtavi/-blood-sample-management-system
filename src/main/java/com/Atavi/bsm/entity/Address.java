package com.Atavi.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String addressLine;
    private String landmark;
    private String city;
    private String state;
    private String country;
    private int postalCode;

// mappedBy creates One extra column in Hospital,User and BloodBank with AddressId as the Foreign Key

    @OneToOne(mappedBy = "address",fetch = FetchType.EAGER) // This Denotes the User is the Owning Side
    private User user;

    @OneToOne(mappedBy = "address",fetch = FetchType.EAGER)
    private Hospital hospital;

    @OneToOne(mappedBy = "address",fetch = FetchType.EAGER)
    private BloodBank bloodBank;

}
