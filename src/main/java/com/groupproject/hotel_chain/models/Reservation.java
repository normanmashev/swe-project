package com.groupproject.hotel_chain.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reservation_id;

    @Column
    private Date checkin_date;

    @Column
    private Date checkout_date;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private Room_Type room_type;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Set<Billing> billings = new HashSet<>();

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Date getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(Date checkin_date) {
        this.checkin_date = checkin_date;
    }

    public Date getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(Date checkout_date) {
        this.checkout_date = checkout_date;
    }

    public Room_Type getRoom_type() {
        return room_type;
    }

    public void setRoom_type(Room_Type room_type) {
        this.room_type = room_type;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Reservation() {
    }

    public Reservation(Date checkin_date, Date checkout_date, Room_Type room_type, Guest guest) {
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.room_type = room_type;
        this.guest = guest;
    }

    public Reservation(Date checkin_date, Date checkout_date, Guest guest) {
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.guest = guest;
    }
}
