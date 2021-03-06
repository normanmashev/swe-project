package com.groupproject.hotel_chain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "room_type")
public class Room_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int room_type_id;

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    private int size;

    @Column
    private int capacity;

    @ElementCollection
    private List<Integer> prices;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @JsonBackReference
    @OneToMany(mappedBy = "room_type", cascade = CascadeType.ALL)
    private Set<Room> rooms = new HashSet<>();

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

    public Set<Room> getRooms() { return rooms; }

    public List<Integer> getPrices() { return prices; }

    public void setPrices(List<Integer> prices) { this.prices = prices; }

    public Room_Type(String name, int size, int capacity, Hotel hotel, List<Integer> prices) {
        this.name = name;
        this.size = size;
        this.capacity = capacity;
        this.hotel = hotel;
        this.prices = prices;
    }
}
