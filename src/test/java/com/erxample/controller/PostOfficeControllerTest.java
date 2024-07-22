package com.erxample.controller;

import com.example.controller.PostOfficeController;
import com.example.dto.PostOfficeDto;
import com.example.model.PostOffice;
import com.example.service.PostOfficeService;
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

public class PostOfficeControllerTest {

    @Mock
    private PostOfficeService postOfficeService;

    @InjectMocks
    private PostOfficeController postOfficeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPostOffices() {
        PostOfficeDto postOfficeDto1 = createPostOfficeDto();
        PostOfficeDto postOfficeDto2 = createPostOfficeDto();
        List<PostOfficeDto> officeDtoList = Arrays.asList(postOfficeDto1, postOfficeDto2);

        when(postOfficeService.getAllPostOfficesDto()).thenReturn(officeDtoList);
        ResponseEntity<List<PostOfficeDto>> response = postOfficeController.getAllPostOffices();
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(officeDtoList, response.getBody());
        when(postOfficeService.getAllPostOfficesDto()).thenReturn(officeDtoList);
    }

    @Test
    public void testGetPostOffice_Exists() {
        PostOffice postOffice = createPostOffice();
        String userId = postOffice.getPostOfficeId();
        when(postOfficeService.getPostOfficeById(userId)).thenReturn(postOffice);
        ResponseEntity<?> response = postOfficeController.getPostOffice(userId);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(postOffice, response.getBody());
    }

    @Test
    public void testGetPostOffice_DoesNotExist() {
        String userId = "1";
        when(postOfficeService.getPostOfficeById(userId)).thenReturn(null);
        ResponseEntity<?> response = postOfficeController.getPostOffice("1");
        assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
        assertEquals("no post office", response.getBody());
    }

    @Test
    public void testSavePostOffice() {
        PostOfficeDto userDto = createPostOfficeDto();
        postOfficeController.savePostOffice(userDto);
        verify(postOfficeService, times(1)).savePostOffice(userDto);
    }

    public PostOffice createPostOffice() {
        PostOffice postOffice = new PostOffice();
        postOffice.setIndex("index_123456_");
        postOffice.setName("name_");
        postOffice.setProperties("properties_");
        return postOffice;
    }

    public PostOfficeDto createPostOfficeDto() {
        PostOffice postOffice = createPostOffice();
        PostOfficeDto postOfficeDto = new PostOfficeDto();
        postOfficeDto.setIndex(postOffice.getIndex());
        postOfficeDto.setProperties(postOffice.getProperties());
        postOfficeDto.setName(postOffice.getName());
        return postOfficeDto;
    }

}
