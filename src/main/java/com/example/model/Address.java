package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id", unique = true)
    private String addressId;

    @Column(name = "address", unique = true)
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_office_id")
    @JsonIgnore
    private PostOffice postOffice;

    @ManyToOne
    @JoinColumn(name = "postal_shipment_id")
    @JsonIgnore
    private PostalShipment postalShipment;

    public Address() {
    }

}
