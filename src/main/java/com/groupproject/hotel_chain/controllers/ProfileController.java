package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.Billing;
import com.groupproject.hotel_chain.models.Reservation;
import com.groupproject.hotel_chain.repository.BillingRepository;
import com.groupproject.hotel_chain.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/profile")
public class ProfileController {
    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/billingHistory")
    public List<Billing> getBillingHistory(@RequestParam(required = true) int guest_id) {
        List<Billing> billings = billingRepository.findAll();
        billings = billings.stream().filter(billing -> billing.getReservation().getGuest().getGuest_id() == guest_id)
                .sorted((b1, b2) -> b1.getReservation().getCheckout_date().compareTo(b2.getReservation().getCheckout_date()))
                .collect(Collectors.toList());

        return billings;
    }

    @GetMapping("/upcomingReservations")
    public List<Reservation> getUpcomingReservations(@RequestParam(required = true) int guest_id) {
        List<Reservation> reservations = reservationRepository.findAll();

        Date curDate = new Date();
        reservations = reservations.stream().filter(reservation -> reservation.getGuest().getGuest_id() == guest_id
                        && curDate.before(reservation.getCheckin_date())).collect(Collectors.toList());

        return reservations;
    }
}
