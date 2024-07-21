package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "postal_shipment")
@Getter
@Setter
public class PostalShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "postal_shipment_id", unique = true)
    private String postalShipmentId;

    @Column(name = "postal_shipment_code", unique = true)
    private String postalShipmentCode;

    @Column(name = "properties")
    private String properties;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_office_send_id", referencedColumnName = "post_office_id")
    private PostOffice postOfficeSend;

    @ManyToOne
    @JoinColumn(name = "post_office_get_id", referencedColumnName = "post_office_id")
    private PostOffice postOfficeGet;

    @Column(nullable = false, name = "postal_status")
    @Enumerated(EnumType.STRING)
    private Collection<PostalStatus> postalStatus;

    @Column(nullable = false, name = "postal_type")
    @Enumerated(EnumType.STRING)
    private Collection<PostalType> postalType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostalShipment that = (PostalShipment) o;
        return Objects.equals(postalShipmentId, that.postalShipmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(postalShipmentId);
    }

    public PostalShipment() {
    }

}
