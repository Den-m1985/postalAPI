package com.erxample.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.example.Main;
import com.example.controller.UserController;
import com.example.dto.UserDto;
import com.example.model.User;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

//@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest() // Specify the main application class
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        UserDto userDto1 =createUserDto();
        UserDto userDto2 = createUserDto();
        List<UserDto> userDtos = Arrays.asList(userDto1, userDto2);

        when(userService.getAllUsersDto()).thenReturn(userDtos);

        mockMvc.perform(get("/api/user/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userId").value(userDto1.getUserId()))
                .andExpect(jsonPath("$[1].userId").value(userDto2.getUserId()));

        verify(userService, times(1)).getAllUsersDto();
    }

    @Test
    public void testGetUser_UserExists() throws Exception {
        User user = createUser();
        String userId = user.getUserId();

        when(userService.getUserById(userId)).thenReturn(user);

        mockMvc.perform(get("/api/user/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(userId));

        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testGetUser_UserDoesNotExist() throws Exception {
        String userId = "1";
        when(userService.getUserById(userId)).thenReturn(null);

        mockMvc.perform(get("/api/user/{id}", userId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("no user"));

        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testSaveUser() throws Exception {
        UserDto userDto = createUserDto();

        mockMvc.perform(post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("user registered"));

        verify(userService, times(1)).saveUser(any(UserDto.class));
    }

    public User createUser() {
        User user = new User();
        user.setFirstName("Name");
        user.setMiddleName("MiddleName");
        user.setLastName("LastName");
        return user;
    }

    public UserDto createUserDto() {
        User user = createUser();

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
       // userDto.setAddress(getAddressId(user));
       // userDto.setPostalShipments(getPostalShipmentId(user));
        return userDto;
    }

}
