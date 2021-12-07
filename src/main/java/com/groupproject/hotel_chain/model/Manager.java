package com.groupproject.hotel_chain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager extends Employee{
    public Manager() {
    }

    public Manager(String name, String surname) {
        super(name, surname);
    }
}
