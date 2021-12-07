package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.*;
import com.groupproject.hotel_chain.repository.HotelRepository;
import com.groupproject.hotel_chain.repository.ReservationRepository;
import com.groupproject.hotel_chain.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/api/deskClerk")
public class DeskClerkController {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private GuestRepository guestRepository;

    @GetMapping("/allReservations")
    public Set<Reservation> showAllReservations(@RequestParam(required = true) int hotel_id) {
        Hotel currentHotel;
        currentHotel = hotelRepository.findById(hotel_id).orElseThrow();
        List<Reservation> allReservations = reservationRepository.findAll();

        Set<Room_Type> hotelRoomTypes = currentHotel.getRoomTypes();
        Set<Reservation> reservationsForHotel = new HashSet<>();

        for (Reservation reservation : allReservations) {
            for (Room_Type roomType : hotelRoomTypes) {
                if (roomType.getRoom_type_id() != reservation.getRoom_type().getRoom_type_id()) {
                    continue;
                }
                reservationsForHotel.add(reservation);
                break;
            }
        }
        return reservationsForHotel;
    }

    @PostMapping("/allReservations/{reservation_id}/delete")
    public void deleteReservation(@PathVariable(required = true) int reservation_id, @RequestParam(required = true) int hotel_id) {
        Reservation reservation = reservationRepository.findById(reservation_id).orElseThrow();
        Hotel hotel = hotelRepository.findById(hotel_id).orElseThrow();
        Set <Room_Type> roomTypes = hotel.getRoomTypes();

        for (Room_Type roomType : roomTypes) {
            if (roomType.getRoom_type_id() != reservation.getRoom_type().getRoom_type_id()) {
                continue;
            }
            Set <Reservation> roomReservations = roomType.getReservations();
            roomReservations.removeIf(roomReservation ->
                roomReservation.getReservation_id() == reservation.getReservation_id()
            );
            reservationRepository.delete(reservation);
            break;
        }
        hotelRepository.save(hotel);
    }

    @PostMapping("/allReservations/create")
    public int createReservation(
            @RequestParam String username,
            @RequestParam int hotel_id,
            @RequestParam String check_in_date,
            @RequestParam String check_out_date,
            @RequestParam int room_type_id
    ) throws ParseException {
        Hotel hotel = hotelRepository.findById(hotel_id).orElseThrow();
        Set<Room_Type> roomTypes = hotel.getRoomTypes();
        Guest currentGuest = guestRepository.findByUsername(username).orElseThrow();
        Room_Type reservationRoomType = new Room_Type();

        Reservation reservation = new Reservation(
                new SimpleDateFormat("yyyy-MM-dd").parse(check_in_date),
                new SimpleDateFormat("yyyy-MM-dd").parse(check_out_date),
                currentGuest
        );

        for (Room_Type roomType : roomTypes) {
            if (roomType.getRoom_type_id() != room_type_id) {
                continue;
            }
            roomType.getReservations().add(reservation);
            reservation.setRoom_type(roomType);
            break;
        }

        guestRepository.save(currentGuest);
        hotelRepository.save(hotel);
        reservationRepository.save(reservation);

        return reservation.getReservation_id();
    }

    @PostMapping("/allReservations/{reservation_id}/edit")
    public int editReservation(
            @PathVariable int reservation_id,
            @RequestParam int hotel_id,
            @RequestParam(required = false) String check_in_date,
            @RequestParam(required = false) String check_out_date,
            @RequestParam(required = false) Integer room_type_id
    ) throws ParseException {
        Reservation reservation = reservationRepository.findById(reservation_id).orElseThrow();
        Hotel hotel = hotelRepository.findById(hotel_id).orElseThrow();
        Set<Room_Type> roomTypes = hotel.getRoomTypes();

        if (check_in_date != null) {
            reservation.setCheckin_date(
                new SimpleDateFormat("yyyy-MM-dd").parse(check_in_date)
            );
        }

        if (check_out_date != null) {
            reservation.setCheckout_date(
                new SimpleDateFormat("yyyy-MM-dd").parse(check_out_date)
            );
        }

        if (room_type_id != null) {
            for (Room_Type roomType : roomTypes) {
                if (!room_type_id.equals(roomType.getRoom_type_id())) {
                    continue;
                }
                reservation.setRoom_type(roomType);
                break;
            }
        }

        reservationRepository.save(reservation);
        hotelRepository.save(hotel);

        return reservation_id;
    }
}

