package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;

    private List<String> address = new ArrayList<>();
    private List<String> postalShipments = new ArrayList<>();

    public UserDto() {
    }
}
