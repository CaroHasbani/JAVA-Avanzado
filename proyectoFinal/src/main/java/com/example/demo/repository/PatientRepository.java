package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Patient;
import com.example.demo.generic.GenericRepository;

import java.util.List;

@Repository
public interface PatientRepository extends GenericRepository<Patient, Integer> {
    public List<Patient> findAll();

    public Patient findByFullName(String fullName);
    public Patient findByBirthDate(String birthDate);
}