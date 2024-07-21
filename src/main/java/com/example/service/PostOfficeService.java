package com.example.service;

import com.example.dto.PostOfficeDto;
import com.example.model.PostOffice;
import com.example.model.PostalShipment;
import com.example.repositiry.PostOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostOfficeService {

    @Autowired
    PostOfficeRepository postOfficeRepository;

    public void savePostOffice(PostOfficeDto postOfficeDto) {
        PostOffice postOffice = new PostOffice();
        postOffice.setName(postOfficeDto.getName());
        postOffice.setIndex(postOfficeDto.getIndex());
        postOffice.setProperties(postOfficeDto.getProperties());
        postOfficeRepository.save(postOffice);
    }

    public PostOffice getPostOfficeById(String id) {
        return postOfficeRepository.findById(id).orElse(null);
    }

    public List<PostOffice> getAllPostOffices() {
        return postOfficeRepository.findAll();
    }

    public List<PostOfficeDto> getAllPostOfficesDto() {
        List<PostOffice> allPostOffices = getAllPostOffices();
        return allPostOffices.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public PostOfficeDto mapToDto(PostOffice postOffice) {
        PostOfficeDto postOfficeDto = new PostOfficeDto();
        postOfficeDto.setIndex(postOffice.getIndex());
        postOfficeDto.setProperties(postOffice.getProperties());
        postOfficeDto.setName(postOffice.getName());
        postOfficeDto.setPostalShipmentArray(getPostalShipmentsId(postOffice));
        return postOfficeDto;
    }

    public List<String> getPostalShipmentsId(PostOffice postOffice) {
        List stringId = new ArrayList<>();
        for (PostalShipment ps : postOffice.getPostalShipmentArray()) {
            String postalShipmentId = ps.getPostalShipmentId();
            stringId.add(postalShipmentId);
        }
        return stringId;
    }

}
