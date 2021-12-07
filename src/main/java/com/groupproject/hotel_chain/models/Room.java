package com.groupproject.hotel_chain.models;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int number;

    @Column
    private int floor;

    @Column(name = "occupied", nullable = false)
    private boolean occupied;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private Room_Type room_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Room_Type getRoom_type() {
        return room_type;
    }

    public void setRoom_type(Room_Type room_type) {
        this.room_type = room_type;
    }

    public Room() {
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Room(int id, int number, int floor, Room_Type room_type) {
        this.id = id;
        this.number = number;
        this.floor = floor;
        this.room_type = room_type;
        this.occupied = false;
    }

    public Room(int id, int number, int floor, Room_Type room_type, boolean occupied) {
        this.id = id;
        this.number = number;
        this.floor = floor;
        this.room_type = room_type;
        this.occupied = occupied;
    }
}
