package com.groupproject.hotel_chain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager extends Employee{
    public Manager() {
    }

    public Manager(int employee_id, String name, String surname) {
        super(employee_id, name, surname);
    }
}
