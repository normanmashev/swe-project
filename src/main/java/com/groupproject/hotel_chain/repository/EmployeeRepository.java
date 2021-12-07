package com.groupproject.hotel_chain.repository;

import com.groupproject.hotel_chain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
