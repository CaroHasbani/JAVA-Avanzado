package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Patient;
import com.example.demo.entity.Rol;
import com.example.demo.entity.VitalSign;
import com.example.demo.generic.GenericService;
import com.example.demo.repository.PatientRepository;
import com.example.demo.responseDTO.PatientDTO;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Propagation;


@Service
public class PatientService extends GenericService<Patient, Integer> {
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public PatientService(PatientRepository pacienteRepository, PasswordEncoder passwordEncoder) {
		super(pacienteRepository);
		this.passwordEncoder = passwordEncoder;
	}

	public List<PatientDTO> listPatients() {
		return repository.findAll()
				.stream()
				.map(PatientDTO::new)
				.collect(Collectors.toList());
	}

	public PatientDTO obtenerPacientePorId(Integer idPaciente) {
		Patient buscado = repository.findById(idPaciente).orElse(null);
		if (buscado != null)
			return new PatientDTO(buscado);
		return null;
	}

	public PatientDTO obtenerPacientePorNombre(String patientName) {
		Patient buscado = ((PatientRepository) repository).findByFullName(patientName);
		if (buscado != null)
			return new PatientDTO(buscado);
		return null;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public PatientDTO addPatient(Patient newPatient) {
		Employee fullName = newEmployee(newPatient.getFullName().replaceAll(" ", "").toLowerCase(),
				passwordEncoder.encode(newPatient.getFullName().replaceAll(" ", "").toLowerCase()), true,
				Arrays.asList(new Rol("ROLE_USER")));
		fullName.getFullName().get(0).setUser(fullName);
		newPatient.setPatient(fullName);
		fullName.setPatient(newPatient);

		newPatient.getVitalSigns().forEach(signoVital -> {
			signoVital.setPatient(newPatient);
		});
		return new PatientDTO(repository.save(newPatient));
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public List<PatientDTO> agregarListaPacientes(List<Patient> listaNueva) {
		List<PatientDTO> lista = new ArrayList<>();	
		listaNueva.forEach(patient -> {
			if (patient.getId() == null)
				lista.add(this.agregarPaciente(patient));
			else
			{
				PatientDTO dto =this.actualizarPaciente(patient.getId(), patient);
				if (dto!=null) 
					lista.add(dto);
			}
		});
		
		return lista;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public PatientDTO agregarSignoVital(Integer idPaciente, SignoVital signoVitalNuevo) {
		Patient pacientaActualizar = repository.findById(idPaciente).orElse(null);
		if (pacientaActualizar != null) {
			pacientaActualizar.addSignoVital(signoVitalNuevo);
			return new PatientDTO(repository.save(pacientaActualizar));
		}
		return null;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public PatientDTO actualizarPaciente(Integer idPaciente, Patient pacienteActualizado) {
		Patient pacienteAActualizar = repository.findById(idPaciente).orElse(null);
		if (pacienteAActualizar != null) {
			pacienteAActualizar.setFullName(pacienteActualizado.getFullName());
			pacienteAActualizar.setBirthDate(pacienteActualizado.getBirthDate());		
//			return new PatientDTO(repository.save(pacienteAActualizar));
			return (this.agregarPaciente(pacienteAActualizar));
		}
		return null;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public boolean borrarPaciente(Integer idPaciente) {
		if(repository.existsById(idPaciente))
		{
			repository.deleteById(idPaciente);
			return true;
		}		
		return false;
	}

}