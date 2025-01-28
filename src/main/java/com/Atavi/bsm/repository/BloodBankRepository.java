package com.Atavi.bsm.repository;

import com.Atavi.bsm.entity.Address;
import com.Atavi.bsm.entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodBankRepository extends JpaRepository<BloodBank, Integer> {

    // Using JPQL
    //From BloodBank b where Address.city in (?,?)

    //MethodNamingConvention
    public List<BloodBank> findByAddress_CityIn(List<String> city);

    /*
    The above query returns:
    List<BloodBank> => List of BloodBanks
    findByAddress => JPA recognizes this as AddressObject
    _ (Undersocre) => Denotes the Attribute of the Address Object is used
    Address_City => JPA understands that City attribute taken from the Address Object
    In => Checks in the given Arguments

     */
}
