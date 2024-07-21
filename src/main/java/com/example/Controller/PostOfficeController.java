package com.example.Controller;

import com.example.dto.PostOfficeDto;
import com.example.model.PostOffice;
import com.example.service.PostOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postoffice")
public class PostOfficeController {

    @Autowired
    private PostOfficeService postOfficeService;

    /**
     * @return PostOffice
     */
    @GetMapping("/all")
    public ResponseEntity<List<PostOfficeDto>> getAllPostOffices() {
        List<PostOfficeDto> postOffices = postOfficeService.getAllPostOfficesDto();
        return ResponseEntity.ok(postOffices);
    }

    /**
     * @return PostOfficeDto by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostOffice(@PathVariable String id) {
        PostOffice postOffice = postOfficeService.getPostOfficeById(id);
        if (postOffice != null) {
            return ResponseEntity.ok(postOffice);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no postalShipment");
        }
    }

    /**
     * @return ok if PostOffice save to DB
     */
    @PostMapping("/save")
    public ResponseEntity<String> login(@RequestBody PostOfficeDto postOfficeDto) {
        postOfficeService.savePostOffice(postOfficeDto);
        return ResponseEntity.ok("post office registered");
    }

}
