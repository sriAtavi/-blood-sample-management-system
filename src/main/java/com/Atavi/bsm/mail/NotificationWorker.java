package com.Atavi.bsm.mail;

import com.Atavi.bsm.entity.*;
import com.Atavi.bsm.exception.BloodBankNotFoundException;
import com.Atavi.bsm.exception.HospitalNotFoundException;
import com.Atavi.bsm.repository.BloodBankRepository;
import com.Atavi.bsm.repository.DonationRequestRepository;
import com.Atavi.bsm.repository.HospitalRepository;
import com.Atavi.bsm.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.Atavi.bsm.enums.OrganizationType.BLOOD_BANK;
import static com.Atavi.bsm.enums.OrganizationType.HOSPITAL;


@AllArgsConstructor
@Component
public class NotificationWorker
{


    private final UserRepository UserRepository;
    private DonationRequestRepository donationRequestRepository;
    private HospitalRepository hospitalRepository;
    private BloodBankRepository bloodBankRepository;
    private UserRepository userRepository;
    private final MailService mailService;

   // private static final Logger logger = LoggerFactory.getLogger(NotificationWorker.class);

    @Getter
    @Setter
    public static class Organization
    {
        private String organizationName;
        private String organizationAddress;
    }

    @Scheduled(fixedRate = 600000) // in MilliSeconds => 1 Minute = 600000miliseconds
   // @Transactional(readOnly = true) // To avoid 'Lazy Loading issues'
    public void sendBloodDonationRequestNotification() throws MessagingException
    {

        List<DonationRequest> donationRequestList = donationRequestRepository.findByRequestCompleted(false);
        System.out.println("Donation Request Query");
        for (DonationRequest request : donationRequestList)
        {
            System.out.println("Donataion Request For Loop");
          Organization org =  this.getOrganizationDetials(request);

            System.out.println("After Fetching Organization Details" + org.getOrganizationName());
          this.sendDonationRequestNotification(org,request);

            System.out.println("After Send Notification Method");

        }

    }

    private void sendDonationRequestNotification(Organization org, DonationRequest request) throws MessagingException
    {

   //     logger.info("Sending donation request notification for organization: {}", org.getOrganizationName());

        // """  STRING  """ => Acts as Text block

        System.out.println("Entered Send Notification Method");
     try {

         System.out.println("Cities: " +request.getCities());
         System.out.println("BloodGroup"+request.getBloodGroup());
      //   List<User> users = userRepository.findByAvailableCityInAndBloodGroupIn(request.getCities(), request.getBloodGroup());

     //    System.out.println("User List" + users);
   //      logger.debug("Found {} users matching the criteria", users.size());
      //   for (User user : users)
       //  {
            // System.out.println("Hi USer"+user);
     //        logger.debug("Preparing to send email to user: {}", user.getEmail());
             Map<String, Object> variables = new HashMap<>();
             variables.put("organizationName", org.getOrganizationName());
//             variables.put("organizationAddress", user.getAddress());

         variables.put("organizationAddress", "Srinagar");

           //  variables.put("bloodGroup", user.getBloodGroup().name());

         variables.put("bloodGroup", request.getBloodGroup());
             //Survey=> need to create Normal Controller (not a RestController) save survey details to DB from the Form
             // & DonationLink needs to be done
             String text = mailService.generateContent("DonationRequest", variables);

          //   mailService.sendMail(user.getEmail(), "Urgent Blood Requirement", text);

         mailService.sendMail("sridargowda13@gmail.com", "Urgent Blood Requirement", text);
          //   logger.info("Email sent to user: {}", user.getEmail());
       //  }
    }
    catch (Exception e)
    {
      //      logger.error("Error occurred while sending donation request notification", e);
            throw e; // Re-throw the exception if necessary
        }
   }

    private Organization getOrganizationDetials(DonationRequest request) {
    Organization organization = new Organization();

        switch (request.getOrganizationType()) {
            case HOSPITAL -> {
                Optional<Hospital> hospitalOptional = hospitalRepository.findByDonationRequest(request);
                if (hospitalOptional.isPresent())
                {
                    Hospital hospital = hospitalOptional.get();
                    organization.setOrganizationName(hospital.getHospitalName());
                    organization.setOrganizationAddress(this.getAddressString(hospital.getAddress()));
                }
                else {
                    System.out.println("Hi");
                    throw new HospitalNotFoundException("Hospital not found");
                }

                return organization;
            }
            case BLOOD_BANK -> {
                Optional<BloodBank> bloodBankOptional = bloodBankRepository.findByDonationRequest(request);
                if (bloodBankOptional.isEmpty())
                    throw new BloodBankNotFoundException("BloodBank not found");

                BloodBank bloodBank = bloodBankOptional.get();
                organization.setOrganizationName(bloodBank.getBloodBankName());
                organization.setOrganizationAddress(this.getAddressString(bloodBank.getAddress()));
                return organization;
            }
            default -> System.out.println("no such organization");
        }
        return organization;
    }

    private String getAddressString(Address address) {
        return address.getAddressLine() +" \n  "+ address.getLandmark() +
                " \n  "+ address.getCity() +" \n  "+ address.getState()
                +" \n  "+ address.getCountry() +" \n  "+ address.getPostalCode();
//        if (address == null) {
//            return "";
//        }
//
//        StringBuilder addressString = new StringBuilder();
//
//        if (address.getAddressLine() != null) {
//            addressString.append(address.getAddressLine());
//        }
//
//        if (address.getLandmark() != null) {
//            if (!addressString.isEmpty()) {
//                addressString.append(", ");
//            }
//            addressString.append(address.getLandmark());
//        }
//
//        if (address.getCity() != null) {
//            if (!addressString.isEmpty()) {
//                addressString.append(", ");
//            }
//            addressString.append(address.getCity());
//        }
//
//        if (address.getState() != null) {
//            if (!addressString.isEmpty()) {
//                addressString.append(", ");
//            }
//            addressString.append(address.getState());
//        }
//
//        if (address.getCountry() != null) {
//            if (!addressString.isEmpty()) {
//                addressString.append(", ");
//            }
//            addressString.append(address.getCountry());
//        }
//
//        if (address.getPostalCode() != 0) { // Assuming postal code 0 is invalid
//            if (!addressString.isEmpty()) {
//                addressString.append(" ");
//            }
//            addressString.append(address.getPostalCode());
//        }
//
//        return addressString.toString();
    }
}
