package com.example.Controller;

import com.example.dto.AddressDto;
import com.example.model.Address;
import com.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * @return all addresses
     */
    @GetMapping("/all")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    /**
     * @param id
     * @return address by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostalShipment(@PathVariable String id) {
        Address address = addressService.getAddressById(id);
        if (address != null) {
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no address");
        }
    }

    /**
     * @return ok if address save to DB
     */
    @PostMapping("/save")
    public ResponseEntity<String> login(@RequestBody AddressDto addressDto) {
        addressService.saveAddress(addressDto);
        return ResponseEntity.ok("address registered");
    }

}
