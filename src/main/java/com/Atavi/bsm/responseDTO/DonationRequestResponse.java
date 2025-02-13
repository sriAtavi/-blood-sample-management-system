package com.Atavi.bsm.responseDTO;

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
public class DonationRequestResponse
{
    private int donationId;
    private LocalDate donationRequestDate;

   // private Date donationRequestDate;
    private LocalTime donationRequestTime;
    private OrganizationType organizationType;
    private List<String> cities;
    private List<BloodGroup> bloodGroup;
}
