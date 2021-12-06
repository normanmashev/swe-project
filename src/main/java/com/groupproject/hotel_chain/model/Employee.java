package com.groupproject.hotel_chain.model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employee_id;

    private String name, surname;

    public int getEmployee_id() {
        return employee_id;
    }

    public Employee() {
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Employee(int employee_id, String name, String surname) {
        this.employee_id = employee_id;
        this.name = name;
        this.surname = surname;
    }
}
