package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Plane;
import com.example.demo.repository.PlaneRepository;

@Service
public class PlaneService {

    @Autowired
    private PlaneRepository planeRepository;

    public Iterable<Plane> getPlanes(){
        return planeRepository.findAll();
    }
    public Plane addPlane(Plane plane){
        return planeRepository.save(plane);
    }
    
    public void deletePlane(Long id){
        planeRepository.deleteById(id);
    }
}


