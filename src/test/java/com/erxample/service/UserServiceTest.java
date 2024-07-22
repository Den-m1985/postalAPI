package com.erxample.service;

import com.example.model.User;
import com.example.repositiry.UserRepository;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsersTest() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(createUser());
        expectedUsers.add(createUser());

        when(userRepository.findAll()).thenReturn(expectedUsers);
        List<User> actualUsers = userService.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
    }


    @Test
    public void testGetUserById_UserExists() {
        User user = createUser();
        String userId = user.getUserId();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User actualUser = userService.getUserById(userId);

        assertNotNull(actualUser);
        assertEquals(userId, actualUser.getUserId());
        assertEquals("Name", actualUser.getFirstName());
        assertEquals("MiddleName", actualUser.getMiddleName());
        assertEquals("LastName", actualUser.getLastName());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetUserById_UserDoesNotExist() {
        String userId = "1";
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User actualUser = userService.getUserById(userId);

        assertNull(actualUser);
        verify(userRepository, times(1)).findById(userId);
    }

    public User createUser() {
        User user = new User();
        user.setFirstName("Name");
        user.setMiddleName("MiddleName");
        user.setLastName("LastName");
        return user;
    }

}
