package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.*;
import com.groupproject.hotel_chain.repository.EmployeeRepository;
import com.groupproject.hotel_chain.repository.GuestRepository;
import com.groupproject.hotel_chain.repository.HotelRepository;
import com.groupproject.hotel_chain.repository.WorkingHoursRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    GuestRepository guestRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    WorkingHoursRepository workingHoursRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestParam(required = true) String username,
                                           @RequestParam(required = true) String password) {
        Optional<Guest> guest = guestRepository.findByUsername(username);
        if (guest.isPresent()) {
            if (passwordEncoder.matches(password, guest.get().getPassword())) {
                return ResponseEntity.ok(guest);
            }
            return ResponseEntity.badRequest().body("Incorrect Password");
        }

        Optional<Employee> employee = employeeRepository.findByUsername(username);
        if (employee.isPresent()) {
            if (passwordEncoder.matches(password, employee.get().getPassword())) {
                return ResponseEntity.ok(employee);
            }
            return ResponseEntity.badRequest().body("Incorrect Password");
        }
        return ResponseEntity.badRequest().body("Incorrect Username");
    }

    @PostMapping("/signup/guest")
    public ResponseEntity<?> registerUser(@RequestParam String username,
                                          @RequestParam String password,
                                           @RequestParam String name,
                                           @RequestParam String surname,
                                           @RequestParam String address,
                                           @RequestParam String home_phone,
                                           @RequestParam String mobile_phone,
                                           @RequestParam Uid_type identification_type,
                                           @RequestParam String number) {
        if (guestRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        Guest guest = new Guest(username,
                passwordEncoder.encode(password),
                identification_type,
                number,
                name,
                surname,
                address,
                home_phone,
                mobile_phone);
        guestRepository.save(guest);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/signup/manager")
    public ResponseEntity<?> registerManager(@RequestParam String username,
                                             @RequestParam String password,
                                             @RequestParam String name,
                                             @RequestParam String surname,
                                             @RequestParam String hotelName,
                                             @RequestParam String address,
                                             @RequestParam List<String> phones) {
        if (employeeRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        Employee employee = new Employee(username,
                passwordEncoder.encode(password),
                name,
                surname,
                "manager");
        employeeRepository.save(employee);
        Hotel hotel = new Hotel(hotelName, address, phones);
        hotelRepository.save(hotel);
        employee.setHotel(hotel);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/signup/employee")
    public ResponseEntity<?> registerEmployee(@RequestParam String username,
                                              @RequestParam String password,
                                              @RequestParam String name,
                                              @RequestParam String surname,
                                              @RequestParam String role,
                                              @RequestParam int hotel_id) {
        Employee employee = new Employee(username,
                passwordEncoder.encode(password),
                name,
                surname,
                role);
        Hotel hotel = hotelRepository.findById(hotel_id).orElseThrow();
        employee.setHotel(hotel);
        employeeRepository.save(employee);
        EnumSet<DayOfWeek> dayOfWeeks = EnumSet.allOf(DayOfWeek.class);
        for (DayOfWeek dayOfWeek : dayOfWeeks) {
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                WorkingHours workingHours = new WorkingHours(null, null, dayOfWeek, employee);
                workingHoursRepository.save(workingHours);
            } else {
                WorkingHours workingHours = new WorkingHours(new Time(9, 00, 00), new Time(18,  00, 00),
                        dayOfWeek, employee);
                workingHoursRepository.save(workingHours);
            }
        }
        return ResponseEntity.ok("");
    }
}
