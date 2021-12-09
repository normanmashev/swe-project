package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.*;
import com.groupproject.hotel_chain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    long getTotalPriceForReservation(Reservation reservation) {
        Room_Type room_type = reservation.getRoom().getRoom_type();

        LocalDateTime date1 = reservation.getCheckin_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime date2 = reservation.getCheckout_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        long totalDays = Duration.between(date1, date2).toDays();

        Date curDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        return room_type.getPrices().get(dayOfWeek - 1) * totalDays;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    void createNewBillings() {
        Date curDate = new Date();
        List<Reservation> reservations = reservationRepository.findAll()
                .stream().filter(reservation -> reservation.getCheckout_date() == curDate)
                .collect(Collectors.toList());

        reservations.forEach(reservation -> {
            Billing billing = new Billing(getTotalPriceForReservation(reservation), "Reservation price", reservation);
            billingRepository.save(billing);
        });
    }


    @GetMapping("/get/all/{hotel_id}")
    public ResponseEntity<?> getAllForHotel(@PathVariable int hotel_id) {
        Hotel hotel = hotelRepository.findById(hotel_id).orElseThrow();
        List<Billing> billings = billingRepository.findAll()
                .stream().filter(billing -> billing.getReservation().getRoom().getRoom_type().getHotel() == hotel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(billings);
    }

    @GetMapping("/get/all/{hotel_id}")
    public ResponseEntity<?> getAllForRoomType(@PathVariable int room_type_id) {
        Room_Type room_type = roomTypeRepository.findById(room_type_id).orElseThrow();
        List<Billing> billings = billingRepository.findAll()
                .stream().filter(billing -> billing.getReservation().getRoom().getRoom_type() == room_type)
                .collect(Collectors.toList());
        return ResponseEntity.ok(billings);
    }

    @GetMapping("/get/all/{guest_id}")
    public ResponseEntity<?> getAllGuest(@PathVariable int guest_id) {
        Guest guest = guestRepository.findById(guest_id).orElseThrow();
        List<Billing> billings = billingRepository.findAll()
                .stream().filter(billing -> billing.getReservation().getGuest() == guest)
                .collect(Collectors.toList());
        return ResponseEntity.ok(billings);
    }
}
