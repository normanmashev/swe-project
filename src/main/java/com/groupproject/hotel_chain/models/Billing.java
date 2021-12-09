package com.groupproject.hotel_chain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bill_id;

    private long price;

    private String feature;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Billing() {
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Billing(long price, String feature, Reservation reservation) {
        this.price = price;
        this.feature = feature;
        this.reservation = reservation;
    }
}
