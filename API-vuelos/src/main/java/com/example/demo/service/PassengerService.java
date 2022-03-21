package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Passenger;
import com.example.demo.repository.PassengerRepository;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Iterable<Passenger> getPassengers(){
        return passengerRepository.findAll();
    }
    public Passenger addPassenger(Passenger passenger){
        return passengerRepository.save(passenger);
    }
    
    public void deletePassenger(Long id){
        passengerRepository.deleteById(id);
    }
}