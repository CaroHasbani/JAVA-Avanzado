package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Patient;
import com.example.demo.entity.VitalSign;
import com.example.demo.responseDTO.PatientDTO;
import com.example.demo.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PreAuthorize("hasAuthority('SCOPE_ROLE_EMPLOYEE')")
    @GetMapping
    public List<PatientDTO> getPatients(){
        return patientService.getPatients();
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    @GetMapping("/findId/{id}")
    public PatientDTO getPatientById(@PathVariable Integer id){
        return patientService.getPatientById(id);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_EMPLOYEE')")
    @GetMapping("/findName/{nombre}")
    public PatientDTO getPatientByName(@PathVariable String nombre){
        return patientService.getPatientByFullName(nombre);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    @GetMapping("/add")
    public PatientDTO addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_EMPLOYEE')")
    @GetMapping("/addVS/{id}")
    public PatientDTO addVitalSign(@PathVariable Integer id, @RequestBody VitalSign vitalSign){
        return patientService.addVitalSign(id, vitalSign);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @PutMapping("/updatePatient/{id}")
    public PatientDTO updatePatient(@PathVariable Integer id, @RequestBody Patient patient){
        return patientService.updatePatient(id, patient);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @DeleteMapping("/deletePatient/{id})")
    public void deletePatient(@PathVariable Integer id){
        patientService.deletePatient(id);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @DeleteMapping("/deletePatientGen/{id}")
    public void deletePatientGen(@PathVariable Integer id){
        patientService.deleteById(id);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @GetMapping("/count")
    public Long count(){
        return patientService.count();
    }
}