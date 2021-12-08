package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.Reservation;
import com.groupproject.hotel_chain.models.Room;
import com.groupproject.hotel_chain.models.Room_Type;
import com.groupproject.hotel_chain.repository.RoomRepository;
import com.groupproject.hotel_chain.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/filter")
public class FilterController {
    public static Date max(Date d1, Date d2) {
        if (d1 == null && d2 == null) return null;
        if (d1 == null) return d2;
        if (d2 == null) return d1;
        return (d1.after(d2)) ? d1 : d2;
    }

    public static Date min(Date d1, Date d2) {
        if (d1 == null && d2 == null) return null;
        if (d1 == null) return d2;
        if (d2 == null) return d1;
        return (d1.before(d2)) ? d1 : d2;
    }
    @Autowired
    RoomRepository roomRepository;

    @PostMapping("/")
    public ResponseEntity<?> filter(@RequestParam Date checkin_date,
                                   @RequestParam Date checkout_date,
                                   @RequestParam int room_type_id) {
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            if (room.getRoom_type().getRoom_type_id() != room_type_id)
                continue;
            boolean reserved = false;
            for (Reservation reservation : room.getReservations()) {
                if (max(reservation.getCheckin_date(), checkin_date).before(min(reservation.getCheckout_date(), checkout_date))) {
                    reserved = true;
                    break;
                }
            }
            if (!reserved) {
                return ResponseEntity.ok(room);
            }
        }
        return ResponseEntity.ok("");
    }
}