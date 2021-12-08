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
    public ResponseEntity<?> addRoomType(@RequestParam int size,
                                         @RequestParam int capacity,
                                         @RequestParam int hotel_id) {
        Hotel hotel = hotelRepository.findById(hotel_id).orElseThrow();
        Room_Type room_type = new Room_Type(size, capacity, hotel);
        hotel.getRoomTypes().add(room_type);
        roomTypeRepository.save(room_type);
        hotelRepository.save(hotel);
        return ResponseEntity.ok(room_type.getRoom_type_id());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editRoomType(@PathVariable int id,
                                          @RequestParam int size,
                                          @RequestParam int capacity,
                                          @RequestParam int hotel_id) {
        Room_Type room_type = roomTypeRepository.findById(id).orElseThrow();
        Hotel hotel1 = room_type.getHotel();
        Hotel hotel2 = hotelRepository.findById(hotel_id).orElseThrow();
        hotel1.getRoomTypes().remove(room_type);
        room_type.setCapacity(capacity);
        room_type.setSize(size);
        room_type.setHotel(hotel2);
        roomTypeRepository.save(room_type);
        hotelRepository.save(hotel1);
        hotelRepository.save(hotel2);
        return ResponseEntity.ok("");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoomType(@PathVariable int id) {
        Room_Type room_type = roomTypeRepository.findById(id).orElseThrow();
        Hotel hotel = room_type.getHotel();
        hotel.getRoomTypes().remove(room_type);
        roomTypeRepository.delete(room_type);
        hotelRepository.save(hotel);
        return ResponseEntity.ok("");
    }
}
