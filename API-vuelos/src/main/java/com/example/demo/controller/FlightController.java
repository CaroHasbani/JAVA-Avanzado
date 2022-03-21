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

import com.example.demo.model.Flight;
import com.example.demo.repository.FlightRepository;
import com.example.demo.service.FlightService;


@RestController
@RequestMapping("/api")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;
    private FlightService flightService;

    @GetMapping("/flights")
    List<Flight> getFlights(){
        return (List<Flight>) flightRepository.findAll();
    }

    @PostMapping("/addFlight")
    Flight addFlight(@RequestBody Flight flight){
        return flightService.addFlight(flight);
    }
    
    @DeleteMapping("/flight/{id}")
    void deleteFlight(@PathVariable Long id){
        flightService.deleteFlight(id);
    }
}