package com.example.fabric;

import com.example.model.*;
import com.example.repositiry.AddressRepository;
import com.example.repositiry.PostOfficeRepository;
import com.example.repositiry.PostalShipmentRepository;
import com.example.repositiry.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class DataInitializer implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    @PersistenceContext
    private EntityManager entityManager;

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
        User user = new User();
        user.setFirstName("Denis");
        user.setMiddleName("Nikolaevich");
        user.setLastName("Denisovich");

        Address address1 = addressRepository.findByAddress("Kaliningrad1").orElse(null);
        if (address1 == null) {
            address1 = new Address();
            address1.setAddress("Kaliningrad1");
        }
        address1.setUser(user);
        user.getAddress().add(address1);
        userRepository.save(user);
        address1.setUser(user);
        addressRepository.save(address1);
        log.info("address1.getAddressId() {}", address1.getAddressId());
        log.info("user {}", user.getUserId());

        // Create a PostOffice for sending
        PostOffice postOfficeSend = new PostOffice();
        postOfficeSend.setName("Post Office 1");
        postOfficeSend.setIndex("123456789");
        postOfficeSend.setProperties("properties");
        postOfficeSend.setUser(user);
        postOfficeSend = postOfficeRepository.save(postOfficeSend);
        log.info("postOfficeSend {}", postOfficeSend.getPostOfficeId());

        // Create a PostOffice for receiving
        PostOffice postOfficeReceive = new PostOffice();
        postOfficeReceive.setName("Post Office 2");
        postOfficeSend.setIndex("987654321");
        postOfficeSend.setProperties("properties");
        postOfficeReceive.setUser(user);
        postOfficeReceive = postOfficeRepository.save(postOfficeReceive);
        log.info("postOfficeReceive {}", postOfficeReceive.getPostOfficeId());

        PostalShipment postalShipment = new PostalShipment();
        postalShipment.setPostalShipmentCode("11111222233333");
        postalShipment.setProperties("Parcel");
        postalShipment.setUser(user);
        postalShipment.setPostOfficeSend(postOfficeSend);
        postalShipment.setPostOfficeGet(postOfficeReceive);
        postalShipment.setPostalStatus(EnumSet.of(PostalStatus.SEND));
        postalShipment.setPostalType(EnumSet.of(PostalType.BANDEROL));
        postalShipment = postalShipmentRepository.save(postalShipment);
        log.info("postalShipment {}", postalShipment.getPostalShipmentId());

    }

}
