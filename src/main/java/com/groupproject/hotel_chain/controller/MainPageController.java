package com.groupproject.hotel_chain.controller;

import com.groupproject.hotel_chain.model.Hotel;
import com.groupproject.hotel_chain.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPageController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/")
    public List<Hotel> main(Model model) {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels;
    }
}
