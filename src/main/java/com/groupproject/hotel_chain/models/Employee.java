package com.groupproject.hotel_chain.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employee_id;

    @Column
    private String username;

    @Column
    private String password;

    private String name, surname;

    private String role;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<WorkingHours> workingHours = new HashSet<>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public Employee() {
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public Set<WorkingHours> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Set<WorkingHours> workingHours) {
        this.workingHours = workingHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee(String username, String password, String name, String surname, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }
}
