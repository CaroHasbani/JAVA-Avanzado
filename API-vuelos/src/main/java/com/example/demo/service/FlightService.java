package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Flight;
import com.example.demo.repository.FlightRepository;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Iterable<Flight> getFlights(){
        return flightRepository.findAll();
    }
    public Flight addFlight(Flight flight){
        return flightRepository.save(flight);
    }
    
    public void deleteFlight(Long id){
        flightRepository.deleteById(id);
    }
}