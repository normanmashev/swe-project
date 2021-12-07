package com.groupproject.hotel_chain.controllers;

import com.groupproject.hotel_chain.models.Employee;
import com.groupproject.hotel_chain.models.Guest;
import com.groupproject.hotel_chain.models.Uid_type;
import com.groupproject.hotel_chain.repository.EmployeeRepository;
import com.groupproject.hotel_chain.repository.GuestRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    GuestRepository guestRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestParam(required = true) String username,
                                           @RequestParam(required = true) String password) {
        Optional<Guest> guest = guestRepository.findByUsername(username);
        if (guest.isPresent()) {
            if (passwordEncoder.matches(password, guest.get().getPassword())) {
                return ResponseEntity.ok("guest");
            }
            return ResponseEntity.badRequest().body("Incorrect Password");
        }

        Optional<Employee> employee = employeeRepository.findbyUsername(username);
        if (employee.isPresent()) {
            if (passwordEncoder.matches(password, employee.get().getPassword())) {
                return ResponseEntity.ok(employee.get().getRole());
            }
            return ResponseEntity.badRequest().body("Incorrect Password");
        }
        return ResponseEntity.badRequest().body("Incorrect Username");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestParam(required = true) String username,
                               @RequestParam(required = true) String password,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String surname,
                               @RequestParam(required = true) String role,
                               @RequestParam(required = false) String address,
                               @RequestParam(required = false) String home_phone,
                               @RequestParam(required = false) String moble_phone,
                               @RequestParam(required = false) Uid_type identification_type,
                               @RequestParam(required = false) String number) {
        if (guestRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        if (employeeRepository.findbyUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        if (role == "guest") {
            Guest guest = new Guest(username,
                    passwordEncoder.encode(password),
                    identification_type,
                    number,
                    name,
                    surname,
                    address,
                    home_phone,
                    moble_phone);
            guestRepository.save(guest);
        } else {
            Employee employee = new Employee(username,
                    passwordEncoder.encode(password),
                    name,
                    surname,
                    role
                    );
            employeeRepository.save(employee);
        }
        return ResponseEntity.ok("Success");
    }
}
