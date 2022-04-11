package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Patient;
import com.example.demo.entity.VitalSign;

import java.util.List;

public interface VitalSignRepository extends CrudRepository<VitalSign, Integer> {
    public List<VitalSign> findAll();

    public List<VitalSign> findByPatient(Patient patient);
}