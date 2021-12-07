package com.groupproject.hotel_chain.repository;

import com.groupproject.hotel_chain.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
}
