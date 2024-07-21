package com.example.repositiry;

import com.example.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    /**
     * @return  Address by id
     */
    Optional<Address> findByAddress(String address);

}
