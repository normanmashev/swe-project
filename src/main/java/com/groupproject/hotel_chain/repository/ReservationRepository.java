package com.groupproject.hotel_chain.repository;

import com.groupproject.hotel_chain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
