package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    //    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id")
//    private Set<Address> address = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Address> address = new HashSet<>();

    //    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "postal_shipment_id")
//    private Set<PostalShipment> postalShipments = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PostalShipment> postalShipments = new HashSet<>();

    //    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "post_office_id")
//    private Set<PostOffice> postOffices = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PostOffice> postOffices = new HashSet<>();

    public User() {
    }

}
