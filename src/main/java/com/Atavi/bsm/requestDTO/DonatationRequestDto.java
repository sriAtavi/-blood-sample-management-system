package com.Atavi.bsm.requestDTO;

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
public class DonatationRequestDto
{

    private LocalDate donationRequestDate;
    private LocalTime donationRequestTime;
    private List<BloodGroup> bloodGroup;
}
