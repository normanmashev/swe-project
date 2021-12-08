package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.Hotel;
import com.groupproject.hotel_chain.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getHotel(@PathVariable(required = true) int id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(hotel);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editHotel(@PathVariable(required = true) int id,
                                       @RequestParam(required = true) String name,
                                       @RequestParam(required = true) String address,
                                       @RequestParam(required = true) List<String> phones) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setPhone(phones);
        return ResponseEntity.ok("");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable(required = true) int id) {
        hotelRepository.deleteById(id);
        return ResponseEntity.ok("");
    }
}
