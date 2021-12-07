package com.groupproject.hotel_chain.repository;

import java.util.Optional;

import com.groupproject.hotel_chain.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    Optional<Guest> findByUsername(String username);
}
