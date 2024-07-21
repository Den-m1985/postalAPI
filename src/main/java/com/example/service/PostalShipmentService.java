package com.example.service;

import com.example.dto.PostalShipmentDto;
import com.example.model.*;
import com.example.repositiry.PostalShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostalShipmentService {

    @Autowired
    PostalShipmentRepository postalShipmentRepository;
    @Autowired
    PostOfficeService postOfficeService;

    public void savePostalShipment(PostalShipmentDto postalShipmentDto) {
        PostalShipment postalShipment = new PostalShipment();
        postalShipment.setPostalShipmentCode(postalShipmentDto.getPostalShipmentCode());
        postalShipment.setProperties(postalShipmentDto.getProperties());
        postalShipment.setPostalStatus(postalShipmentDto.getPostalStatus());
        postalShipment.setPostalType(postalShipmentDto.getPostalType());
        postalShipmentRepository.save(postalShipment);
    }

    public PostalShipment getPostalShipmentById(String id) {
        return postalShipmentRepository.findById(id).orElse(null);
    }

    public List<String> getTrack(String id) {
        List<String> array = new ArrayList<>();
        PostalShipment postalShipmentList = getPostalShipmentById(id);
        for (PostOffice postOffice : postalShipmentList.getPostOfficeTrack()) {
            String psId = postOffice.getPostOfficeId();
            PostOffice newPostOffice = postOfficeService.getPostOfficeById(psId);
            array.add(newPostOffice.getPostOfficeAddress().getAddress());
        }
        return array;
    }

    public List<PostalShipment> getAllPostalShipments() {
        return postalShipmentRepository.findAll();
    }

    public List<PostalShipmentDto> getAllPostalShipmentDto() {
        List<PostalShipment> postalShipmentList = getAllPostalShipments();
        return postalShipmentList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public PostalShipmentDto mapToDto(PostalShipment postalShipment) {
        PostalShipmentDto postalShipmentDto = new PostalShipmentDto();
        postalShipmentDto.setPostalShipmentCode(postalShipment.getPostalShipmentCode());
        postalShipmentDto.setPostalStatus(postalShipment.getPostalStatus());
        postalShipmentDto.setPostalType(postalShipment.getPostalType());
        postalShipmentDto.setUserId(getUserId(postalShipment));
        postalShipmentDto.setPostOfficeTrack(getPostOfficeTrackId(postalShipment));
        postalShipmentDto.setPostOfficeSend(getPostOfficeSendId(postalShipment));
        postalShipmentDto.setPostOfficeGet(getPostOfficeGetId(postalShipment));
        postalShipment = postalShipmentRepository.save(postalShipment);
        return postalShipmentDto;
    }

    public String getUserId(PostalShipment postalShipment) {
        User user = postalShipment.getUser();
        String userId = null;
        if (user != null) {
            userId = user.getUserId();
        }
        return userId;
    }

    public List<String> getPostOfficeTrackId(PostalShipment postalShipment) {
        List stringId = new ArrayList<>();
        for (PostOffice postOffice : postalShipment.getPostOfficeTrack()) {
            String psId = postOffice.getPostOfficeId();
            stringId.add(psId);
        }
        return stringId;
    }

    public String getPostOfficeSendId(PostalShipment postalShipment) {
        PostOffice user = postalShipment.getPostOfficeSend();
        String userId = null;
        if (user != null) {
            userId = user.getPostOfficeId();
        }
        return userId;
    }

    public String getPostOfficeGetId(PostalShipment postalShipment) {
        PostOffice user = postalShipment.getPostOfficeGet();
        String userId = null;
        if (user != null) {
            userId = user.getPostOfficeId();
        }
        return userId;
    }

}
