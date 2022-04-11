package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Patient;
import com.example.demo.entity.VitalSign;
import com.example.demo.repository.VitalSignRepository;
import com.example.demo.responseDTO.VitalSignDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VitalSignService {
    @Autowired
    private VitalSignRepository vitalSignRepository;
// métodos get
    public List<VitalSignDTO> getVitalSigns(){
        return vitalSignRepository.findAll()
                .stream()
                .map(VitalSignDTO::new)
                .collect(Collectors.toList());
    }

    public List<VitalSignDTO> getVitalSignsByPatient(Patient patient){
        return vitalSignRepository.findByPatient(patient)
                .stream()
                .map(VitalSignDTO::new)
                .collect(Collectors.toList());
    }
// actualizar signos vitales
    public List<VitalSignDTO> updateVitalSigns(VitalSign vitalSign, Integer id){
        VitalSign sv = vitalSignRepository.findById(id).orElse(null);
        if(sv != null){
            sv.setVitalSign(vitalSign.getVitalSign());
            sv.setValor(vitalSign.getValor());
            sv = vitalSignRepository.save(sv);
            return sv.getPatient().getVitalSigns()
                    .stream()
                    .map(VitalSignDTO::new)
                    .collect(Collectors.toList());
        }
        return null;
    }
// eliminar signo vital
    public void deleteVitalSign(Integer id){
        VitalSign vs = vitalSignRepository.findById(id).orElse(null);
        if(vs != null)
            vitalSignRepository.deleteById(id);
    }
}