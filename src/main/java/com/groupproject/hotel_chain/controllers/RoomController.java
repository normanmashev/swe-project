package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.Room;
import com.groupproject.hotel_chain.models.Room_Type;
import com.groupproject.hotel_chain.repository.HotelRepository;
import com.groupproject.hotel_chain.repository.ReservationRepository;
import com.groupproject.hotel_chain.repository.RoomRepository;
import com.groupproject.hotel_chain.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/get/all/{room_type_id}")
    public ResponseEntity<?> getRoomsForRoomTypeId(@PathVariable int room_type_id) {
        Room_Type room_type = roomTypeRepository.findById(room_type_id).orElseThrow();
        return ResponseEntity.ok(room_type.getRooms());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoomType(@RequestParam int number,
                                         @RequestParam int floor,
                                         @RequestParam int room_type_id) {
        Room_Type room_type = roomTypeRepository.findById(room_type_id).orElseThrow();
        Room room = new Room(number, floor, room_type);
        roomRepository.save(room);
        return ResponseEntity.ok(room.getId());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editRoomType(@PathVariable int id,
                                          @RequestParam(required = false) Integer number,
                                          @RequestParam(required = false) Integer floor) {
        Room room = roomRepository.findById(id).orElseThrow();
        if (number != null) {
            room.setNumber(number);
        }
        if (floor != null) {
            room.setFloor(floor);
        }
        roomRepository.save(room);
        return ResponseEntity.ok("");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoomType(@PathVariable int id) {
        Room room = roomRepository.findById(id).orElseThrow();
        roomRepository.delete(room);
        return ResponseEntity.ok("");
    }
}
