package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.Hotel;
import com.groupproject.hotel_chain.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/get/all")
    public ResponseEntity<?> getHotels(@RequestParam(required = false) String city) {
        List<Hotel> hotels = hotelRepository.findAll();
        if (city != null) {
            hotels.stream().filter(hotel -> hotel.getAddress() == city).collect(Collectors.toList());
        }
        return ResponseEntity.ok(hotels);
    }

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
        hotelRepository.findById(id).orElseThrow();
        hotelRepository.deleteById(id);
        return ResponseEntity.ok("");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addHotel(@RequestParam(required = true) String name,
                                      @RequestParam(required = true) String address,
                                      @RequestParam(required = true) List<String> phones) {
        Hotel hotel = new Hotel(name, address, phones);
        hotelRepository.save(hotel);
        return ResponseEntity.ok(hotel.getHotel_id());
    }
}
