package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.Employee;
import com.groupproject.hotel_chain.models.WorkingHours;
import com.groupproject.hotel_chain.repository.EmployeeRepository;
import com.groupproject.hotel_chain.repository.WorkingHoursRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/workinghours")
public class WorkingHoursController {

    @Autowired
    WorkingHoursRepository workingHoursRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/schedules")
    public List<WorkingHours> getAllSchedules() {
        return workingHoursRepository.findAll();
    }

    @GetMapping("/schedule")
    public Set<WorkingHours> getSchedule(@RequestParam int employee_id) {
        Employee employee = employeeRepository.findById(employee_id).orElseThrow();
        return employee.getWorkingHours();
    }

//    @PostMapping("/edit")
//    public ResponseEntity<?> editSchedule(@RequestParam int id,
//                                          @RequestParam Time start,
//                                          @RequestParam Time end,
//                                          @RequestParam Date date) {
//        WorkingHours workingHours = workingHoursRepository.findById(id).orElseThrow();
//
//    }
}
