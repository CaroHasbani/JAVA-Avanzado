package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.example.demo.entity.Patient;
import com.example.demo.entity.VitalSign;
import com.example.demo.generic.GenericService;
import com.example.demo.repository.PatientRepository;
import com.example.demo.responseDTO.PatientDTO;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService extends GenericService<Patient, Integer> {
    @Autowired
    public PatientService(PatientRepository patientRepository) {
        super(patientRepository);
    }
    //métodos get
    public List<PatientDTO> getPatients(){
        return genericRepository.findAll()
                .stream()
                .map(PatientDTO::new)
                .collect(Collectors.toList());
    }

    public PatientDTO getPatientById(Integer id){
        Patient foundPatient = genericRepository.findById(id).orElse(null);
        if(foundPatient != null)
            return new PatientDTO(foundPatient);
        return null;
    }

    public PatientDTO getPatientByFullName(String fullName){
        Patient foundPatient = ((PatientRepository) genericRepository).findByFullName(fullName);
        if(foundPatient != null)
            return new PatientDTO(foundPatient);
        return null;
    }
    
    public PatientDTO getPatientByBirthDate(String birthDate){
        Patient foundPatient = ((PatientRepository) genericRepository).findByBirthDate(birthDate);
        if(foundPatient != null)
            return new PatientDTO(foundPatient);
        return null;
    }
//agregar pacientes
    @Transactional
    public PatientDTO addPatient(Patient newPatient){
        newPatient.getVitalSigns().forEach(vs -> vs.setPatient(newPatient));  
        return new PatientDTO(genericRepository.save(newPatient));
    }

    @Transactional
    public List<PatientDTO> addPatientList(List<Patient> patientList){
        List<PatientDTO> list = new ArrayList<>();
        patientList.forEach(patient -> {
            if(patient.getId() == null){
                list.add(this.addPatient(patient));
            } else {
                list.add(this.updatePatient(patient.getId(), patient));
            }
        });
        return list;
    }
// agregar signos vitales
    @Transactional
    public PatientDTO addVitalSign(Integer id, VitalSign newVitalSign){
        Patient foundPatient = genericRepository.findById(id).orElse(null);
        if(foundPatient != null){
            foundPatient.addSignoVital(newVitalSign);
            return new PatientDTO(genericRepository.save(foundPatient));
        }
        return null;
    }
// actualizar paciente
    @Transactional
    public PatientDTO updatePatient(Integer id, Patient updatedPatient){
        Patient foundPatient = genericRepository.findById(id).orElse(null);
        if(foundPatient != null){
            foundPatient.setFullName(updatedPatient.getFullName());
            foundPatient.setBirthDate(updatedPatient.getBirthDate());
            foundPatient.setPed(updatedPatient.getPed());
            return new PatientDTO(foundPatient);
        }
        return null;
    }
// eliminar paciente
    public void deletePatient(Integer id){
        genericRepository.deleteById(id);
    }
}