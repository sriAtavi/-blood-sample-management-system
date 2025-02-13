package com.Atavi.bsm.requestDTO;

import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.enums.OrganizationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonatationRequestDto
{

    // private Date donationRequestDate;

    private LocalDate donationRequestDate;
    private LocalTime donationRequestTime;
    private List<BloodGroup> bloodGroup;
    private OrganizationType organizationType;
    private List<String> cities;
}
