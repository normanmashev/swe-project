package com.groupproject.hotel_chain.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int hotel_id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<Room_Type> room_types = new HashSet<>();


    public Hotel() {
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Hotel(int hotel_id, String name, String address, String phone) {
        this.hotel_id = hotel_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}