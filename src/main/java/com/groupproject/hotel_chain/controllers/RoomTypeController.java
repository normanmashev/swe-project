package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.Hotel;
import com.groupproject.hotel_chain.models.Reservation;
import com.groupproject.hotel_chain.models.Room;
import com.groupproject.hotel_chain.models.Room_Type;
import com.groupproject.hotel_chain.models.dto.HotelRoomTypeDto;
import com.groupproject.hotel_chain.repository.HotelRepository;
import com.groupproject.hotel_chain.repository.ReservationRepository;
import com.groupproject.hotel_chain.repository.RoomRepository;
import com.groupproject.hotel_chain.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/roomtype")
public class RoomTypeController {
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomRepository roomRepository;

    private List<Integer> defaultPrices = new ArrayList<>(List.of(100, 100, 100, 100, 100, 100, 100));

    @GetMapping("/get/all/{id}")
    public ResponseEntity<?> getRoomTypesForHotelId(@PathVariable int id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(hotel.getRoomTypes());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoomType(@RequestParam String name,
                                         @RequestParam int size,
                                         @RequestParam int capacity,
                                         @RequestParam int hotel_id,
                                         @RequestParam(required = false) List<Integer> prices) {
        Hotel hotel = hotelRepository.findById(hotel_id).orElseThrow();
        if (prices.size() != 7) {
            prices = defaultPrices;
        }
        Room_Type room_type = new Room_Type(name, size, capacity, hotel, prices);
        roomTypeRepository.save(room_type);
        return ResponseEntity.ok(room_type.getRoom_type_id());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editRoomType(@PathVariable int id,
                                          @RequestParam int size,
                                          @RequestParam int capacity,
                                          @RequestParam List<Integer> prices) {
        Room_Type room_type = roomTypeRepository.findById(id).orElseThrow();
        room_type.setCapacity(capacity);
        room_type.setSize(size);
        room_type.setPrices(prices);
        roomTypeRepository.save(room_type);
        return ResponseEntity.ok("");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoomType(@PathVariable int id) {
        Room_Type room_type = roomTypeRepository.findById(id).orElseThrow();
        roomTypeRepository.delete(room_type);
        return ResponseEntity.ok("");
    }

    @GetMapping("/find")
    public ResponseEntity<?> find(@RequestParam String check_in_date,
                                  @RequestParam String check_out_date,
                                  @RequestParam int capacity) throws ParseException {

        Date checkin_date = new SimpleDateFormat("yyyy-MM-dd").parse(check_in_date);
        Date checkout_date = new SimpleDateFormat("yyyy-MM-dd").parse(check_out_date);

        Set<Room_Type> room_Types = new HashSet<Room_Type>();
        List<Room> rooms = roomRepository.findAll();
        List<Reservation> reservations = reservationRepository.findAll();

        for (Room room : rooms) {
            if (room_Types.contains(room.getRoom_type())) {
                continue;
            }
            boolean reserved = false;
            for (Reservation reservation : room.getReservations()) {
                if ((checkin_date.after(reservation.getCheckin_date()) && checkin_date.before(reservation.getCheckout_date()))
                        || (checkout_date.after(reservation.getCheckin_date()) && checkout_date.before(reservation.getCheckout_date()))) {
                    reserved = true;
                    break;
                }
            }
            if (!reserved) {
                room_Types.add(room.getRoom_type());
            }
        }


        List<HotelRoomTypeDto> hotelRoomTypeDtos = new ArrayList<>();
        for (Room_Type room_type : room_Types) {
            HotelRoomTypeDto oldHotelRoomTypeDto = null;
            for (HotelRoomTypeDto hotelRoomTypeDto : hotelRoomTypeDtos) {
                if (hotelRoomTypeDto.getHotel() == room_type.getHotel()) {
                    oldHotelRoomTypeDto = hotelRoomTypeDto;
                    break;
                }
            }
            if (oldHotelRoomTypeDto == null) {
                oldHotelRoomTypeDto = new HotelRoomTypeDto(room_type.getHotel());
                hotelRoomTypeDtos.add(oldHotelRoomTypeDto);
            }
            oldHotelRoomTypeDto.addRoom_Type(room_type);
        }
        return ResponseEntity.ok(hotelRoomTypeDtos);
    }
}
