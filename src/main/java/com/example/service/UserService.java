package com.example.service;

import com.example.dto.UserDto;
import com.example.model.Address;
import com.example.model.PostalShipment;
import com.example.model.User;
import com.example.repositiry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserDto> getAllUsersDto() {
        List<User> users = getAllUsers();
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setAddress(getAddressId(user));
        userDto.setPostalShipments(getPostalShipmentId(user));
        return userDto;
    }

    public List<String> getAddressId(User user) {
        List stringId = new ArrayList<>();
        for (Address addr : user.getAddress()) {
            String addressId = addr.getAddressId();
            stringId.add(addressId);
        }
        return stringId;
    }

    public List<String> getPostalShipmentId(User user) {
        List stringId = new ArrayList<>();
        for (PostalShipment ps : user.getPostalShipments()) {
            String psId = ps.getPostalShipmentId();
            stringId.add(psId);
        }
        return stringId;
    }

}
