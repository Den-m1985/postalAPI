package com.example.fabric;

import com.example.model.*;
import com.example.repositiry.AddressRepository;
import com.example.repositiry.PostOfficeRepository;
import com.example.repositiry.PostalShipmentRepository;
import com.example.repositiry.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UserRepository userRepository;
    private final PostOfficeRepository postOfficeRepository;
    private final AddressRepository addressRepository;
    private final PostalShipmentRepository postalShipmentRepository;

    public DataInitializer(UserRepository userRepository, PostOfficeRepository postOfficeRepository, AddressRepository addressRepository, PostalShipmentRepository postalShipmentRepository) {
        this.userRepository = userRepository;
        this.postOfficeRepository = postOfficeRepository;
        this.addressRepository = addressRepository;
        this.postalShipmentRepository = postalShipmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        fillData();
    }

    public void fillData() {
        // create objects
        int counts = 5;
        User user = createUser();
        List<Address> addresses = createAddresses(counts);
        List<PostOffice> postOffices = createPostOffices(counts);
        PostalShipment postalShipment = createPostalShipment();

        // fill empty fields of objects
        Address thirdAddress = addresses.stream()
                .skip(counts - 3)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Address not found"));
        thirdAddress.setUser(user);
        addressRepository.save(thirdAddress);
        user.setAddress(addresses);
        User savedUser = userRepository.save(user);

        // fill address in post office
        int i = 0;
        for (Address address : addresses) {
            String str = address.getAddress();
            PostOffice newPostOffice = postOffices.get(i);
            newPostOffice.setPostOfficeAddress(address);
            postOfficeRepository.save(newPostOffice);
            i++;
        }

        // fill empty fields in postel shipment
        postalShipment.setUser(savedUser);
        postalShipment.setPostOfficeTrack(postOffices);
        postalShipment.setPostOfficeSend(postOffices.get(0));
        postalShipment.setPostOfficeGet(postOffices.get(counts - 1));
        postalShipment = postalShipmentRepository.save(postalShipment);

        // show track
        showTrack(postalShipment);

        //change status
        postalShipment.setPostalStatus(PostalStatus.READYTOGET);
        postalShipmentRepository.save(postalShipment);

    }

    public User createUser() {
        User user = new User();
        user.setFirstName("Name");
        user.setMiddleName("MiddleName");
        user.setLastName("LastName");
        user = userRepository.save(user);
        log.info("user id {}", user.getUserId());
        return user;
    }

    public List<Address> createAddresses(int count) {
        List<Address> addressArray = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Address address = new Address();
            address.setAddress("address_" + i);
            address = addressRepository.save(address);
            addressArray.add(address);
            //log.info("address_id {}", address.getAddressId());
        }
        return addressArray;
    }

    public List<PostOffice> createPostOffices(int count) {
        List<PostOffice> postOffices = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            PostOffice postOffice = new PostOffice();
            postOffice.setIndex("index_123456_" + i);
            postOffice.setName("name_" + i);
            postOffice.setProperties("properties_" + i);
            postOffice = postOfficeRepository.save(postOffice);
            postOffices.add(postOffice);
            //log.info("postOffice_id {}", postOffice.getPostOfficeId());
        }
        return postOffices;
    }

    public PostalShipment createPostalShipment() {
        PostalShipment postalShipment = new PostalShipment();
        postalShipment.setPostalShipmentCode("987654");
        postalShipment.setProperties("properties");
        postalShipment.setPostalStatus(PostalStatus.SEND);
        postalShipment.setPostalType(PostalType.BANDEROL);
        postalShipment = postalShipmentRepository.save(postalShipment);
        //log.info("postal_shipment_id {}", postalShipment.getPostalShipmentId());
        return postalShipment;
    }

    public void showTrack(PostalShipment postalShipment) {
        log.info("post shipping track:");
        for (PostOffice postOffice : postalShipment.getPostOfficeTrack()) {
            String psId = postOffice.getPostOfficeId();
            PostOffice newPostOffice = postOfficeRepository.findById(psId).orElse(null);
            log.info("PostOffice {}", newPostOffice.getPostOfficeAddress().getAddress());
        }
    }

}
