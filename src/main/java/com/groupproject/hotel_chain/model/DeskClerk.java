package com.groupproject.hotel_chain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "desk_clerk")
public class DeskClerk extends Employee{
    public DeskClerk() {
    }

    public DeskClerk(int employee_id, String name, String surname) {
        super(employee_id, name, surname);
    }
}
