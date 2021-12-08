package com.groupproject.hotel_chain.repository;

import com.groupproject.hotel_chain.models.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Integer> {
}
