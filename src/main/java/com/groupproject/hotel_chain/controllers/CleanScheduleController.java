package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.Reservation;
import com.groupproject.hotel_chain.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class CleanScheduleController {

    @Autowired
    private RoomRepository roomRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    void uncleanOccupiedRooms() {
        setClean(false);
    }

    @Scheduled(cron = "0 0 10 * * ?")
    void cleanOccupiedRooms() {
        setClean(true);
    }

    void setClean(boolean clean) {
        Date curDate = new Date();
        roomRepository.findAll().forEach(room -> {
            boolean isOccupied = false;
            for (Reservation reservation : room.getReservations()) {
                if (curDate.after(reservation.getCheckin_date()) && curDate.before(reservation.getCheckout_date())) {
                    isOccupied = true;
                    break;
                }
            }
            if (isOccupied) {
                room.setClean(false);
            }
        });
    }
}
