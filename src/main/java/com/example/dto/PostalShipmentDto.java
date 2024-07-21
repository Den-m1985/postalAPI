package com.example.dto;

import com.example.model.PostalStatus;
import com.example.model.PostalType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostalShipmentDto {

    private String postalShipmentCode;
    private String properties;
    private String userId;
    private List<String> postOfficeTrack;
    private String postOfficeSend;
    private String postOfficeGet;
    private PostalStatus postalStatus;
    private PostalType postalType;

    public PostalShipmentDto() {
    }
}
