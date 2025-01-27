package com.Atavi.bsm.requestDTO;

import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.enums.AdminType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {

    private User user;

    //private AdminType adminType;
}
