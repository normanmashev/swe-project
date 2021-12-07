package com.groupproject.hotel_chain.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room_type")
public class Room_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int room_type_id;

    @Column
    private int size;

    @Column
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room_type", cascade = CascadeType.ALL)
    private Set<Room> roomSet = new HashSet<>();

    public Room_Type() {
    }

    public int getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(int room_type_id) {
        this.room_type_id = room_type_id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room_Type(int size, int capacity, Hotel hotel) {
        this.size = size;
        this.capacity = capacity;
        this.hotel = hotel;
    }
}
