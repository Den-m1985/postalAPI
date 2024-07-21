package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostOfficeDto {

    private String properties;
    private String index;
    private String name;
    private List<String> PostalShipmentArray;

    public PostOfficeDto() {
    }

}
