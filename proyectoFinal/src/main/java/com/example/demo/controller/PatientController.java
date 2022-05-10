package com.example.demo.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Patient;
import com.example.demo.entity.VitalSign;
import com.example.demo.responseDTO.PatientDTO;
import com.example.demo.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Patient", description = "API de pacientes")
public class PatientController {

	@Autowired
	private PatientService patientService;

	// Muestra los pacientes
	@Operation(summary = "List all patients", description = "Service to obtain the list of all patients.")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@GetMapping
	@ResponseStatus( code = HttpStatus.OK)
	public List<PatientDTO> getPatients() {
		return patientService.getPatients();
	}

	// Muestra pacientes por id
	@Operation(summary = "Patient by ID", description = "Service to get a patient by their ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Patient found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))),
			@ApiResponse(responseCode = "401", description = "Failed to auth", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
	@GetMapping("/buscarPorId/{patientID}")
	public ResponseEntity<PatientDTO> getPatientById(
			@Parameter(name = "patientID", description = "Patient id", allowEmptyValue = false) @PathVariable Integer patientID) {
		PatientDTO dto = patientService.getPatientById(patientID);
		return (dto != null ? ResponseEntity.ok().body(dto) : ResponseEntity.notFound().build());
	}

	// Muestra los pacientes por nombre
	@Operation(summary = "Patient by name", description = "Service to get a patient by name.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Patient found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))),
			@ApiResponse(responseCode = "401", description = "Failed to auth", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@GetMapping("/getByFullName/{patientFullName}")
	public ResponseEntity<PatientDTO> getPatientByFullName(@PathVariable String patientFullName) {
		PatientDTO dto = patientService.getPatientByFullName(patientFullName);
		return (dto != null ? ResponseEntity.ok().body(dto) : ResponseEntity.notFound().build());
	}

	// Agregar pacientes
	@Operation(summary = "Add patient", description = "Service to add a patient.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Patient added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))),
			@ApiResponse(responseCode = "401", description = "Failed to auth", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@PostMapping("/addPatient")
	public ResponseEntity<PatientDTO> addPatient(@RequestBody Patient pacienteNuevo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(pacienteNuevo));
	}

	// Agregar lista de pacientes
	@Operation(summary = "Add patient list", description = "Service to add a list of patients.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Patient lista added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))),
			@ApiResponse(responseCode = "401", description = "Failed to auth", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@PostMapping("/addPatients")
	public ResponseEntity<List<PatientDTO>> addPatientList(@RequestBody List<Patient> list) {
		return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatientList(list));
	}

	// AGREGA UN SIGNO VITAL A UN PACIENTE
	@Operation(summary = "Add vital sign", description = "Service to add a vital sign to a patient using their ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Vital Sign added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))),
			@ApiResponse(responseCode = "401", description = "Failed to auth", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@PutMapping("/addVitalSignToPatient/{patientID}")
	public ResponseEntity<PatientDTO> agregarSignoVital(@PathVariable Integer patientID,
			@RequestBody VitalSign vitalSignNew) {
		PatientDTO dto = patientService.addVitalSign(patientID, vitalSignNew);
		return (dto != null ? ResponseEntity.status(HttpStatus.CREATED).body(dto)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	//Actualizar paciente
	@Operation(summary = "Update name and date of birth", description = "Service to update a patient's name and date of birth.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Patient updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))),
			@ApiResponse(responseCode = "401", description = "Failed to auth", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@PutMapping("/updatePatient/{patientID}")
	public ResponseEntity<PatientDTO> updatePatient(@PathVariable Integer patientID, @RequestBody Patient updatedPatient) {
		PatientDTO dto = patientService.updatePatient(patientID, updatedPatient);		
		return (dto != null ? ResponseEntity.status(HttpStatus.CREATED).body(dto)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// Borrar un paciente
	  @PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
	    @DeleteMapping("/deletePatient/{id}")
	    @Operation(summary = "Delete patient", description = "Service to eliminate a patient")
	    public void deletePatient(@PathVariable Integer id){
	        patientService.deletePatient(id);
	    }



	// Count
	@Operation(summary = "Number of patients", description = "Service to obtain the total number of patients in the system.")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
	@GetMapping("/count")
	@ResponseStatus(code = HttpStatus.OK)
	public Long count() {
		return patientService.count();
	}

}