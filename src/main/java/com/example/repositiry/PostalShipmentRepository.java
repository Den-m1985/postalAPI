package com.example.repositiry;

import com.example.model.PostalShipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostalShipmentRepository extends JpaRepository<PostalShipment, String> {

    Optional<PostalShipment> findByPostalShipmentId(String postalShipmentId);

}
