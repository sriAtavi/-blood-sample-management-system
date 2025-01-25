package com.Atavi.bsm.responseDTO;

import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.enums.Gender;
import com.Atavi.bsm.enums.UserRole;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse // While providing response to the user/client
{
    private int userId;
    private String userName;
    private LocalDate lastDonatedAt;
    private Gender gender;
    private String availableCity;
    private boolean verified;
    private BloodGroup bloodGroup;
    private UserRole userRole;
}
