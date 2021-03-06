package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.Employee;
import com.groupproject.hotel_chain.models.Hotel;
import com.groupproject.hotel_chain.repository.EmployeeRepository;
import com.groupproject.hotel_chain.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("api/employee/")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllEmployees(@RequestParam int hotel_id) {
        Hotel hotel = hotelRepository.findById(hotel_id).orElseThrow();
        Set<Employee> employees = hotel.getEmployees();
        for (Employee employee : employees) {
            if (employee.getRole().equals("manager")) {
                employees.remove(employee);
                break;
            }
        }
        return ResponseEntity.ok(employees);
    }

    @PostMapping("edit/salary")
    public ResponseEntity<?> editSalary(@RequestParam int employee_id,
                                        @RequestParam int salary) {
        Employee employee = employeeRepository.findById(employee_id).orElseThrow();
        employee.setSalary(salary);
        employeeRepository.save(employee);
        return ResponseEntity.ok("");
    }
}
