package com.groupproject.hotel_chain.repository;

import com.groupproject.hotel_chain.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}
