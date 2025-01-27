package com.Atavi.bsm.entity;

import com.Atavi.bsm.enums.AdminType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @OneToOne
    private User user;

    //This won't be used since we are creating User Roles in User Entity itself instead of creating Admin Types and User Types seperately
   // private AdminType adminType;
}
