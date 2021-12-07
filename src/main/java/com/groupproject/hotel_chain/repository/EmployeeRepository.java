package com.groupproject.hotel_chain.repository;

import com.groupproject.hotel_chain.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
