package com.groupproject.hotel_chain.model;

import javax.persistence.*;

@Entity
@Table(name = "billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bill_id;

    private int amount;

    private String feature;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public Billing(int amount, String feature, Reservation reservation) {
        this.amount = amount;
        this.feature = feature;
        this.reservation = reservation;
    }
}
