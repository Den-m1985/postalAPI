package com.example.Controller;

import com.example.dto.PostalShipmentDto;
import com.example.model.PostalShipment;
import com.example.service.PostalShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postalshipment")
public class PostalShipmentController {

    @Autowired
    private PostalShipmentService postalShipmentService;

    /**
     * @return all PostalShipment
     */
    @GetMapping("/all")
    public ResponseEntity<List<PostalShipmentDto>> getAllPostalShipments() {
        List<PostalShipmentDto> postalShipments = postalShipmentService.getAllPostalShipmentDto();
        return ResponseEntity.ok(postalShipments);
    }

    /**
     * @return PostalShipment by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostalShipment(@PathVariable String id) {
        PostalShipment postalShipment = postalShipmentService.getPostalShipmentById(id);
        if (postalShipment != null) {
            return ResponseEntity.ok(postalShipment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no postalShipment");
        }
    }

    /**
     * @return ok if PostalShipment save to DB
     */
    @PostMapping("/save")
    public ResponseEntity<String> login(@RequestBody PostalShipmentDto postalShipmentDto) {
        postalShipmentService.savePostalShipment(postalShipmentDto);
        return ResponseEntity.ok("postal shipment registered");
    }

    /**
     *
     * @return all addresses by PostalShipment id
     */
    @GetMapping("/track/{id}")
    public ResponseEntity<List<String>> getTrack(@PathVariable String id) {
        List<String> postalShipments = postalShipmentService.getTrack(id);
        return ResponseEntity.ok(postalShipments);
    }

}
