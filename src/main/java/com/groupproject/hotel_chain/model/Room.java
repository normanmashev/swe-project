package com.groupproject.hotel_chain.model;

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

    public Room(int number, int floor, Room_Type room_type) {
        this.number = number;
        this.floor = floor;
        this.room_type = room_type;
    }
}
