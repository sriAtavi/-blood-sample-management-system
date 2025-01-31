package com.Atavi.bsm.responseDTO;

import com.Atavi.bsm.enums.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationRequestResponse
{
    private int donationId;
    private LocalDate donationRequestDate;
    private LocalTime donationRequestTime;

    private List<BloodGroup> bloodGroup;
}
