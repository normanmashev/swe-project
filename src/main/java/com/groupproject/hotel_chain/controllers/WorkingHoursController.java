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
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping("/schedule/{id}")
    public Set<WorkingHours> getSchedule(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return employee.getWorkingHours();
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editSchedule(@RequestParam int employee_id,
                                          @RequestParam Time start,
                                          @RequestParam Time end,
                                          @RequestParam DayOfWeek dayOfWeek) {
        Employee employee = employeeRepository.findById(employee_id).orElseThrow();
        Set<WorkingHours> workingHours = employee.getWorkingHours();
        for (WorkingHours workingHour : workingHours) {
            if (workingHour.getDayOfWeek() == dayOfWeek) {
                workingHour.setStart_time(start);
                workingHour.setEnd_time(end);
                workingHoursRepository.save(workingHour);
                break;
            }
        }
        return ResponseEntity.ok("");
    }
}
