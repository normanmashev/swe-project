package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.Hotel;
import com.groupproject.hotel_chain.models.Room_Type;
import com.groupproject.hotel_chain.repository.HotelRepository;
import com.groupproject.hotel_chain.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roomtype")
public class RoomTypeController {
    @Autowired
    RoomTypeRepository roomTypeRepository;
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/get/all/{id}")
    public ResponseEntity<?> getRoomTypesForHotelId(@PathVariable int id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(hotel.getRoomTypes());
    }

    @PostMapping("/add/")
    public ResponseEntity<?> addRoomType(@RequestParam String name,
                                         @RequestParam int size,
                                         @RequestParam int capacity,
                                         @RequestParam int hotel_id) {
        Hotel hotel = hotelRepository.findById(hotel_id).orElseThrow();
        Room_Type room_type = new Room_Type(name, size, capacity, hotel);
        roomTypeRepository.save(room_type);
        return ResponseEntity.ok(room_type.getRoom_type_id());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editRoomType(@PathVariable int id,
                                          @RequestParam int size,
                                          @RequestParam int capacity) {
        Room_Type room_type = roomTypeRepository.findById(id).orElseThrow();
        room_type.setCapacity(capacity);
        room_type.setSize(size);
        roomTypeRepository.save(room_type);
        return ResponseEntity.ok("");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoomType(@PathVariable int id) {
        Room_Type room_type = roomTypeRepository.findById(id).orElseThrow();
        roomTypeRepository.delete(room_type);
        return ResponseEntity.ok("");
    }
}
