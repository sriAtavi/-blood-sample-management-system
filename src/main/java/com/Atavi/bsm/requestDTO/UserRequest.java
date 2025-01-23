package com.Atavi.bsm.requestDTO;

import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.enums.Gender;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor // This is used to serialize
public class UserRequest //While giving request from user
{ //DTO=> Data Transfer Object ( What all the objects to be transferred)

    @NotNull(message = "user name cannot be null")  //This is from "jakarta.validation.constraints.NotNull" to validate fields
    @NotBlank(message = "user name cannot be blank")
    @Pattern(regexp = "$[a-zA-Z_]^",message = "Should Contain Only Alphabets and Underscore")
    private String userName;

    @NotNull(message = "password cannot be null")
    @NotBlank(message = "user name cannot be blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$\n",message = "Password SHould contain 8 characters")
    private String password;

    /*
    ^ : Start of the string
    (?=.*[a-z]) : At least one lowercase letter
    (?=.*[A-Z]) : At least one uppercase letter
    (?=.*\d) : At least one digit
    (?=.*[!@#$%^&*]) : At least one special character
     */


    private String email;
    private Long phoneNumber;

    @Min(18)
    @Max(90)
    private int age;

    private Gender gender;
    private String availableCity;
    private BloodGroup bloodGroup;
    private LocalDate lastDonatedAt;
}
