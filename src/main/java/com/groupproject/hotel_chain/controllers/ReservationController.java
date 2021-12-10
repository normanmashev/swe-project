package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.*;
import com.groupproject.hotel_chain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

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
    private BillingRepository billingRepository;

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

    void createNewBillings(Reservation reservation) {
        Billing billing = new Billing(getTotalPriceForReservation(reservation), "Reservation price", reservation);
        billingRepository.save(billing);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addReservation(@RequestParam String username,
                                            @RequestParam String check_in_date,
                                            @RequestParam String check_out_date,
                                            @RequestParam int room_id) throws ParseException {
        Room room = roomRepository.findById(room_id).orElseThrow();
        Guest guest = guestRepository.findByUsername(username).orElseThrow();
        Date checkin_date = new SimpleDateFormat("yyyy-MM-dd").parse(check_in_date);
        Date checkout_date = new SimpleDateFormat("yyyy-MM-dd").parse(check_out_date);
        Reservation reservation = new Reservation(checkin_date, checkout_date, room, guest);
        reservationRepository.save(reservation);
        room.getReservations().add(reservation);
        roomRepository.save(room);
        createNewBillings(reservation);
        return ResponseEntity.ok(reservation.getReservation_id());
    }

//    @PostMapping("/create")
//    public ResponseEntity<?> createReservation(
//            @RequestParam String username,
//            @RequestParam String check_in_date,
//            @RequestParam String check_out_date,
//            @RequestParam int room_type_id) throws Exception {
//        Date checkin_date = new SimpleDateFormat("yyyy-MM-dd").parse(check_in_date);
//        Date checkout_date = new SimpleDateFormat("yyyy-MM-dd").parse(check_out_date);
//        Room room = findRoom(checkin_date, checkout_date, room_type_id);
//
//        if (room == null) {
//            throw new Exception("No room found with this type");
//        }
//
//        Guest currentGuest = guestRepository.findByUsername(username).orElseThrow();
//
//        Reservation newReservation = new Reservation(checkin_date, checkout_date, currentGuest);
//        reservationRepository.save(newReservation);
//
//        return ResponseEntity.ok(newReservation.getReservation_id());
//    }

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
            oldReservation.setCheckout_date(checkout_date);
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

