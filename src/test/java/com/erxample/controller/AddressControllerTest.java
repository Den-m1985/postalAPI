package com.erxample.controller;

import com.example.controller.AddressController;
import com.example.dto.AddressDto;
import com.example.model.Address;
import com.example.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAddresses() {
        Address address1 = createAddress();
        Address address2 = createAddress();
        List<Address> addressList = Arrays.asList(address1, address2);

        when(addressService.getAllAddresses()).thenReturn(addressList);
        ResponseEntity<List<Address>> response = addressController.getAllAddresses();
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(addressList, response.getBody());
        when(addressService.getAllAddresses()).thenReturn(addressList);
    }

    @Test
    public void testGetAddress_AddressExists() {
        Address address = createAddress();
        String userId = address.getAddressId();
        when(addressService.getAddressById(userId)).thenReturn(address);
        ResponseEntity<?> response = addressController.getAddressById(userId);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(address, response.getBody());
    }

    @Test
    public void testGetAddress_AddressDoesNotExist() {
        String userId = "1";
        when(addressService.getAddressById(userId)).thenReturn(null);
        ResponseEntity<?> response = addressController.getAddressById("1");
        assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
        assertEquals("no address", response.getBody());
    }

    @Test
    public void testSaveAddress() {
        AddressDto addressDto = createAddressDto();
        addressController.saveAddress(addressDto);
        verify(addressService, times(1)).saveAddress(addressDto);
    }

    public Address createAddress() {
        Address address = new Address();
        address.setAddress("address");
        return address;
    }

    public AddressDto createAddressDto() {
        Address address = createAddress();
        AddressDto userDto = new AddressDto();
        userDto.setAddress(address.getAddress());
        return userDto;
    }

}
