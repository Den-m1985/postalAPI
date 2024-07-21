package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_office_id")
    private PostOffice postOffice;

    @ManyToOne
    @JoinColumn(name = "postal_shipment_id")
    private PostalShipment postalShipment;

//    public Address(String address) {
//        this.address = address;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(addressId, address1.addressId) && Objects.equals(address, address1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, address);
    }

    public Address() {
    }

}
