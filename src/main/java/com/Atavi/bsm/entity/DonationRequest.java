package com.Atavi.bsm.entity;

import com.Atavi.bsm.enums.BloodGroup;
import com.Atavi.bsm.enums.OrganizationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DonationRequest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donationId;

    @CreationTimestamp // Since we are specifying this variable as LocalDate => @CreationTimeStamp will get only the Date
    private LocalDate donationRequestDate;


    @CreationTimestamp // Here only Time Stamp will be fetched
    private LocalTime donationRequestTime;

    private boolean requestCompleted; // This need no to be used in DTOs

    @ElementCollection(fetch = FetchType.EAGER) // This creates the Separate Table without having any relation with other Entity
    private List<String> cities;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<BloodGroup> bloodGroup;

    private OrganizationType organizationType;

//    @ManyToOne
//    private Donation donation;



}
