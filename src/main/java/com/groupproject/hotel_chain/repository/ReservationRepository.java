package com.groupproject.hotel_chain.repository;

import com.groupproject.hotel_chain.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
