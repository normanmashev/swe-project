package com.groupproject.hotel_chain.repository;

import com.groupproject.hotel_chain.models.Room_Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<Room_Type, Integer> {
}
