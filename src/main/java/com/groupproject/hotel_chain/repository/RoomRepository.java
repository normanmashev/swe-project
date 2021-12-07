package com.groupproject.hotel_chain.repository;

import com.groupproject.hotel_chain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
