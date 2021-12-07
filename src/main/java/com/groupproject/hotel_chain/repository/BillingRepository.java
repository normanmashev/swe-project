package com.groupproject.hotel_chain.repository;

import com.groupproject.hotel_chain.model.DeskClerk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<DeskClerk, Integer> {
}
