package com.groupproject.hotel_chain.repository;

import com.groupproject.hotel_chain.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
