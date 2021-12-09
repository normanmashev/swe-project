package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.Guest;
import com.groupproject.hotel_chain.models.Hotel;
import com.groupproject.hotel_chain.models.Reservation;
import com.groupproject.hotel_chain.models.Room;
import com.groupproject.hotel_chain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @GetMapping("/allReservations")
    public Set<Reservation> showAllReservations(@RequestParam(required = true) int hotel_id) {
        Hotel currentHotel = hotelRepository.findById(hotel_id).orElseThrow();
        List<Reservation> allReservations = reservationRepository.findAll();

        Set<Reservation> reservationsForHotel = new HashSet<>();

        for (Reservation reservation : allReservations) {
            if (reservation.getRoom().getRoom_type().getHotel().getHotel_id() == hotel_id) {
                reservationsForHotel.add(reservation);
            }
        }
        return reservationsForHotel;
    }

    public Room findRoom(Date checkin_date,
                         Date checkout_date,
                         int room_type_id) {
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            if (room.getRoom_type().getRoom_type_id() != room_type_id)
                continue;
            boolean reserved = false;
            for (Reservation reservation : room.getReservations()) {
                if ((checkin_date.after(reservation.getCheckin_date()) && checkin_date.before(reservation.getCheckout_date()))
                || (checkout_date.after(reservation.getCheckin_date()) && checkout_date.before(reservation.getCheckout_date()))) {
                    reserved = true;
                    break;
                }
            }
            if (!reserved) {
                return room;
            }
        }
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createReservation(
            @RequestParam String username,
            @RequestParam String check_in_date,
            @RequestParam String check_out_date,
            @RequestParam int room_type_id) throws Exception {
        Date checkin_date = new SimpleDateFormat("yyyy-MM-dd").parse(check_in_date);
        Date checkout_date = new SimpleDateFormat("yyyy-MM-dd").parse(check_out_date);
        Room room = findRoom(checkin_date, checkout_date, room_type_id);

        if (room == null) {
            throw new Exception("No room found with this type");
        }

        Guest currentGuest = guestRepository.findByUsername(username).orElseThrow();

        Reservation newReservation = new Reservation(checkin_date, checkout_date, currentGuest);
        reservationRepository.save(newReservation);

        return ResponseEntity.ok(newReservation.getReservation_id());
    }

    @PostMapping("/{reservation_id}/edit")
    public ResponseEntity<?> editReservation(
            @PathVariable int reservation_id,
            @RequestParam(required = false) String check_in_date,
            @RequestParam(required = false) String check_out_date,
            @RequestParam(required = false) Integer room_id
    ) throws Exception {
        Reservation oldReservation = reservationRepository.findById(reservation_id).orElseThrow();

        Date curDate = new Date();

        if (check_in_date != null) {
            Date checkin_date = new SimpleDateFormat("yyyy-MM-dd").parse(check_in_date);
            if (curDate.after(checkin_date)) {
                throw new Exception("Cannot edit check in date");
            }
            oldReservation.setCheckin_date(checkin_date);
        }

        if (check_out_date != null) {
            Date checkout_date = new SimpleDateFormat("yyyy-MM-dd").parse(check_out_date);
            if (curDate.after(checkout_date) || curDate == checkout_date) {
                throw new Exception("Cannot edit check out date");
            }
            oldReservation.setCheckin_date(checkout_date);
        }

        if (room_id != null) {
            Room room = roomRepository.findById(room_id).orElseThrow();
            oldReservation.setRoom(room);
        }

        boolean reserved = false;
        for (Reservation reservation : oldReservation.getRoom().getReservations()) {
            if (reservation.getReservation_id() == oldReservation.getReservation_id())
                continue;
            if ((oldReservation.getCheckin_date().after(reservation.getCheckin_date()) && oldReservation.getCheckin_date().before(reservation.getCheckout_date()))
                    || (oldReservation.getCheckout_date().after(reservation.getCheckin_date()) && oldReservation.getCheckout_date().before(reservation.getCheckout_date()))) {
                reserved = true;
                break;
            }
        }
        if (reserved) {
            throw new Exception("The room is not available in this date range");
        }


        reservationRepository.save(oldReservation);

        return ResponseEntity.ok("");
    }

    @PostMapping("/{reservation_id}/delete")
    public ResponseEntity<?> deleteReservation(@PathVariable(required = true) int reservation_id, @RequestParam(required = true) int hotel_id) {
        Reservation reservation = reservationRepository.findById(reservation_id).orElseThrow();
        reservationRepository.delete(reservation);
        return ResponseEntity.ok("");
    }
}

