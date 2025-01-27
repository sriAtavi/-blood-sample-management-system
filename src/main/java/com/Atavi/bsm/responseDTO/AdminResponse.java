package com.Atavi.bsm.responseDTO;

import com.Atavi.bsm.entity.User;
import com.Atavi.bsm.enums.AdminType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponse {

    private int adminId;

    private User user;

   // private AdminType adminType;
}

