package com.Atavi.bsm.responseDTO;

import com.Atavi.bsm.entity.BloodBank;
import com.Atavi.bsm.entity.Hospital;
import com.Atavi.bsm.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private int addressId;
    private String addressLine;
    private String landmark;
    private String city;
    private String state;
    private String country;
    private int postalCode;
}
