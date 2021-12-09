package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.*;
import com.groupproject.hotel_chain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("api/billing/")
public class BillingController {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    @GetMapping("/get/all/hotel/{hotel_id}")
    public ResponseEntity<?> getAllForHotel(@PathVariable int hotel_id) {
        Hotel hotel = hotelRepository.findById(hotel_id).orElseThrow();
        List<Billing> billings = billingRepository.findAll()
                .stream().filter(billing -> billing.getReservation().getRoom().getRoom_type().getHotel() == hotel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(billings);
    }

    @GetMapping("/get/all/room/{room_type_id}")
    public ResponseEntity<?> getAllForRoomType(@PathVariable int room_type_id) {
        Room_Type room_type = roomTypeRepository.findById(room_type_id).orElseThrow();
        List<Billing> billings = billingRepository.findAll()
                .stream().filter(billing -> billing.getReservation().getRoom().getRoom_type() == room_type)
                .collect(Collectors.toList());
        return ResponseEntity.ok(billings);
    }

    @GetMapping("/get/all/guest/{guest_id}")
    public ResponseEntity<?> getAllGuest(@PathVariable int guest_id) {
        Guest guest = guestRepository.findById(guest_id).orElseThrow();
        List<Billing> billings = billingRepository.findAll()
                .stream().filter(billing -> billing.getReservation().getGuest() == guest)
                .collect(Collectors.toList());
        return ResponseEntity.ok(billings);
    }
}
