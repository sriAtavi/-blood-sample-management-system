package com.Atavi.bsm.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeadResponse
{
    private int leadId;
    private LocalDate leadDate;
    private LocalTime leadTime;
}
