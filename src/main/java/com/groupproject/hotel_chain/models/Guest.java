package com.groupproject.hotel_chain.models;

import javax.persistence.*;

@Entity
@Table(name = "guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int guest_id;

    @Enumerated(EnumType.STRING)
    private Uid_type identification_type;

    @Column
    private String identification_number;

    @Column
    private String username;

    @Column
    private String password;

    private String name, surname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    private String address;

    @Column
    private String home_phone;

    @Column
    private String mobile_phone;

    public Guest() {
    }

    public int getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    public Uid_type getIdentification_type() {
        return identification_type;
    }

    public void setIdentification_type(Uid_type identification_type) {
        this.identification_type = identification_type;
    }

    public String getIdentification_number() {
        return identification_number;
    }

    public void setIdentification_number(String identification_number) {
        this.identification_number = identification_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public Guest(String username, String password,
                 Uid_type identification_type,
                 String identification_number,
                 String name,
                 String surname,
                 String address,
                 String home_phone,
                 String mobile_phone) {
        this.identification_type = identification_type;
        this.identification_number = identification_number;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.home_phone = home_phone;
        this.mobile_phone = mobile_phone;
    }
}
