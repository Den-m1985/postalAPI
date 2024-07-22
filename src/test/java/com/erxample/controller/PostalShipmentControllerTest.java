package com.erxample.controller;

import com.example.controller.PostalShipmentController;
import com.example.dto.PostalShipmentDto;
import com.example.model.PostalShipment;
import com.example.model.PostalStatus;
import com.example.model.PostalType;
import com.example.service.PostalShipmentService;
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

public class PostalShipmentControllerTest {

    @Mock
    private PostalShipmentService postalShipmentService;

    @InjectMocks
    private PostalShipmentController postalShipmentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPostalShipments() {
        PostalShipmentDto address1 = createPostalShipmentDto();
        PostalShipmentDto address2 = createPostalShipmentDto();
        List<PostalShipmentDto> addressList = Arrays.asList(address1, address2);

        when(postalShipmentService.getAllPostalShipmentDto()).thenReturn(addressList);
        ResponseEntity<List<PostalShipmentDto>> response = postalShipmentController.getAllPostalShipments();
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(addressList, response.getBody());
        when(postalShipmentService.getAllPostalShipmentDto()).thenReturn(addressList);
    }

    @Test
    public void testGetPostalShipment_Exists() {
        PostalShipment postalShipment = createPostalShipment();
        String userId = postalShipment.getPostalShipmentId();
        when(postalShipmentService.getPostalShipmentById(userId)).thenReturn(postalShipment);
        ResponseEntity<?> response = postalShipmentController.getPostalShipment(userId);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(postalShipment, response.getBody());
    }

    @Test
    public void testGetPostalShipment_DoesNotExist() {
        String userId = "1";
        when(postalShipmentService.getPostalShipmentById(userId)).thenReturn(null);
        ResponseEntity<?> response = postalShipmentController.getPostalShipment("1");
        assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
        assertEquals("no postalShipment", response.getBody());
    }

    @Test
    public void testSavePostalShipment() {
        PostalShipmentDto postalShipmentDto = createPostalShipmentDto();
        postalShipmentController.savePostalShipment(postalShipmentDto);
        verify(postalShipmentService, times(1)).savePostalShipment(postalShipmentDto);
    }

    public PostalShipment createPostalShipment() {
        PostalShipment postalShipment = new PostalShipment();
        postalShipment.setPostalShipmentCode("987654");
        postalShipment.setProperties("properties");
        postalShipment.setPostalStatus(PostalStatus.SEND);
        postalShipment.setPostalType(PostalType.BANDEROL);
        return postalShipment;
    }

    public PostalShipmentDto createPostalShipmentDto() {
        PostalShipment postalShipment = createPostalShipment();
        PostalShipmentDto postalShipmentDto = new PostalShipmentDto();
        postalShipment.setPostalShipmentCode(postalShipmentDto.getPostalShipmentCode());
        postalShipment.setProperties(postalShipmentDto.getProperties());
        postalShipment.setPostalStatus(postalShipmentDto.getPostalStatus());
        postalShipment.setPostalType(postalShipmentDto.getPostalType());
        return postalShipmentDto;
    }

}
