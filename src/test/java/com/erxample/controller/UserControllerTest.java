package com.erxample.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.example.controller.UserController;
import com.example.dto.UserDto;
import com.example.model.User;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        UserDto userDto1 = createUserDto();
        UserDto userDto2 = createUserDto();
        List<UserDto> userDtos = Arrays.asList(userDto1, userDto2);

        when(userService.getAllUsersDto()).thenReturn(userDtos);
        ResponseEntity<List<UserDto>> response = userController.getAllUsers();
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(userDtos, response.getBody());
        when(userService.getAllUsersDto()).thenReturn(userDtos);
    }

    @Test
    public void testGetUser_UserExists() {
        User user = createUser();
        String userId = user.getUserId();
        when(userService.getUserById(userId)).thenReturn(user);
        ResponseEntity<?> response = userController.getUser(userId);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetUser_UserDoesNotExist() {
        String userId = "1";
        when(userService.getUserById(userId)).thenReturn(null);
        ResponseEntity<?> response = userController.getUser("1");
        assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
        assertEquals("no user", response.getBody());
    }

    @Test
    public void testSaveUser() {
        UserDto userDto = createUserDto();
        userController.saveUser(userDto);
        verify(userService, times(1)).saveUser(userDto);
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
        return userDto;
    }

}
