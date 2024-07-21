package com.example.service;

import com.example.dto.AddressDto;
import com.example.model.Address;
import com.example.repositiry.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void saveAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setAddress(addressDto.getAddress());
        addressRepository.save(address);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(String id) {
        return addressRepository.findById(id).orElse(null);
    }

}
