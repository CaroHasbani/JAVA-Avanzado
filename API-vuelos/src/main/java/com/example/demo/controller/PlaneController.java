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

import com.example.demo.model.Plane;
import com.example.demo.repository.PlaneRepository;
import com.example.demo.service.PlaneService;


@RestController
@RequestMapping("/api")
public class PlaneController {
    @Autowired
    private PlaneRepository planeRepository;
    private PlaneService planeService;

    @GetMapping("/planes")
    List<Plane> getPlanes(){
        return (List<Plane>) planeRepository.findAll();
    }

    @PostMapping("/addPlane")
    Plane addPlane(@RequestBody Plane plane){
        return planeService.addPlane(plane);
    }
    
    @DeleteMapping("/plane/{id}")
    void deletePlane(@PathVariable Long id){
        planeService.deletePlane(id);
    }
}



