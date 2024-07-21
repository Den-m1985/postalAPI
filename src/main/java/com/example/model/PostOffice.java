package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "post_office")
@Getter
@Setter
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "post_office_id", unique = true)
    private String postOfficeId;

    @Column(name = "properties")
    private String properties;

    @Column(name = "index", unique = true)
    private String index;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address postOfficeAddress;

    @ManyToMany
    @JoinTable(name = "employee_task",
            joinColumns = @JoinColumn(name = "post_office_id", referencedColumnName = "post_office_id"),
            inverseJoinColumns = @JoinColumn(name = "postal_shipment_id", referencedColumnName = "postal_shipment_id"))
    private Collection<PostalShipment> PostalShipmentArray;

    public PostOffice() {
    }

}
