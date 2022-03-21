package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Passenger;
import com.example.demo.repository.PassengerRepository;
import com.example.demo.service.PassengerService;


@RestController
@RequestMapping("/api")
public class PassengerController {
    @Autowired
    private PassengerRepository passengerRepository;
    private PassengerService passengerService;

    @GetMapping("/passengers")
    List<Passenger> getPassengers(){
        return (List<Passenger>) passengerRepository.findAll();
    }

    @PostMapping("/addPassenger")
    Passenger addPassenger(@RequestBody Passenger passenger){
        return passengerService.addPassenger(passenger);
    }
    
    @DeleteMapping("/passenger/{id}")
    void deletePassenger(@PathVariable Long id){
        passengerService.deletePassenger(id);
    }
}