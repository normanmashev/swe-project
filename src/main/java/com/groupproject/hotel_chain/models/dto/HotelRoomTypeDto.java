package com.groupproject.hotel_chain.models.dto;

import com.groupproject.hotel_chain.models.Hotel;
import com.groupproject.hotel_chain.models.Room;
import com.groupproject.hotel_chain.models.Room_Type;

import java.util.Collections;
import java.util.List;


public class HotelRoomTypeDto {
    private Hotel hotel;
    private List<Room_Type> room_types;

    public Hotel getHotel() {return hotel; };

    public void addRoom_Type(Room_Type room_type) {
        room_types.add(room_type);
    }

    public HotelRoomTypeDto(Hotel hotel) {
        this.hotel = hotel;
        this.room_types = Collections.emptyList();
    }
}
