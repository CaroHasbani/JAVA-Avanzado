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

import com.example.demo.responseDTO.PatientDTO;
import com.example.demo.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Paciente", description = "API de pacientes")
public class PatientController {

	@Autowired
	private PatientService patientService;

	// MUESTRA TODOS LOS PACIENTES
	@Operation(summary = "Listar todos los pacientes", description = "Servicio para obtener el listado de todos los pacientes.")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@GetMapping
	@ResponseStatus( code = HttpStatus.OK)
	public List<PatientDTO> listarPacientes() {
		return patientService.listarPacientes();
	}

	// MUESTRA LOS DATOS DE UN PACIENTE SOLICITADO POR EL ID
	@Operation(summary = "Paciente por ID", description = "Servicio para obtener un paciente por su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Paciente encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))),
			@ApiResponse(responseCode = "401", description = "Fallo la autenticación", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "No se encontro el paciente con el ID especificado", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
	@GetMapping("/buscarPorId/{idPaciente}")
	public ResponseEntity<PatientDTO> obtenerPacientePorId(
			@Parameter(name = "idPaciente", description = "Id del Paciente", allowEmptyValue = false) @PathVariable Integer idPaciente) {
		PatientDTO dto = patientService.obtenerPacientePorId(idPaciente);
		return (dto != null ? ResponseEntity.ok().body(dto) : ResponseEntity.notFound().build());
	}

	// MUESTRA LOS DATOS DE UN PACIENTE SOLICITADO POR EL NOMBRE
	@Operation(summary = "Paciente por Nombre", description = "Servicio para obtener un paciente por su Nombre.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Paciente encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))),
			@ApiResponse(responseCode = "401", description = "Fallo la autenticación", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "No se encontro el paciente con el Nombre especificado", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@GetMapping("/buscarPorNombre/{nombrePaciente}")
	public ResponseEntity<PatientDTO> obtenerPacientePorNombre(@PathVariable String nombrePaciente) {
		PatientDTO dto = patientService.obtenerPacientePorNombre(nombrePaciente);
		return (dto != null ? ResponseEntity.ok().body(dto) : ResponseEntity.notFound().build());
	}

	// AGREGA UN PACIENTE NUEVO
	@Operation(summary = "Agregar un paciente", description = "Servicio para agregar un paciente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Paciente agregado satisfactoriamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))),
			@ApiResponse(responseCode = "401", description = "Fallo la autenticación", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@PostMapping("/agregarPaciente")
	public ResponseEntity<PatientDTO> agregarPaciente(@RequestBody Paciente pacienteNuevo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(patientService.agregarPaciente(pacienteNuevo));
	}

	// AGREGA UNA LISTA DE PACIENTES
	@Operation(summary = "Agregar lista de pacientes", description = "Servicio para agregar una lista de pacientes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Lista de pacientes agregada satisfactoriamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))),
			@ApiResponse(responseCode = "401", description = "Fallo la autenticación", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@PostMapping("/agregarPacientes")
	public ResponseEntity<List<PatientDTO>> agregarListaPacientes(@RequestBody List<Paciente> lista) {
		return ResponseEntity.status(HttpStatus.CREATED).body(patientService.agregarListaPacientes(lista));
	}

	// AGREGA UN SIGNO VITAL A UN PACIENTE
	@Operation(summary = "Agregar signo vital", description = "Servicio para agregar un signo vital a un paciente mediante su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Se agregaron los signos vitales satisfactoriamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))),
			@ApiResponse(responseCode = "401", description = "Fallo la autenticación", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "No se encontro el paciente con el ID especificado", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@PutMapping("/agregarSignoVitalAPaciente/{idPaciente}")
	public ResponseEntity<PatientDTO> agregarSignoVital(@PathVariable Integer idPaciente,
			@RequestBody SignoVital signoVitalNuevo) {
		PatientDTO dto = patientService.agregarSignoVital(idPaciente, signoVitalNuevo);
		return (dto != null ? ResponseEntity.status(HttpStatus.CREATED).body(dto)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// ACTUALIZA EL NOMBRE Y LA FECHA DE NACIMIENTO DE UN PACIENTE
	@Operation(summary = "Actualizar nombre y fecha de nacimiento", description = "Servicio para actualizar el nombre y la fecha de nacimiento de un paciente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Se actualizo el paciente satisfactoriamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))),
			@ApiResponse(responseCode = "401", description = "Fallo la autenticación", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "No se encontro el paciente con el ID especificado", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@PutMapping("/actualizarPaciente/{idPaciente}")
	public ResponseEntity<PatientDTO> actualizarPaciente(@PathVariable Integer idPaciente, @RequestBody Paciente pacienteActualizado) {
		PatientDTO dto = patientService.actualizarPaciente(idPaciente, pacienteActualizado);		
		return (dto != null ? ResponseEntity.status(HttpStatus.CREATED).body(dto)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// BORRAR UN PACIENTE Y SUS SIGNOS VITALES
	@Operation(summary = "Borrar un paciente", description = "Servicio para borrar un paciente por su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Se actualizo el paciente satisfactoriamente", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Fallo la autenticación", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "No se encontro el paciente con el ID especificado", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
	@DeleteMapping("/borrarPaciente/{idPaciente}")
	public ResponseEntity<?> borrarPaciente(@PathVariable Integer idPaciente) {		
		return (patientService.borrarPaciente(idPaciente)?ResponseEntity.status(HttpStatus.NO_CONTENT).build():ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}

	// BORRAR UN PACIENTE Y SUS SIGNOS VITALES (GENERIC)
	@Operation(summary = "Borrar paciente mediante generics", description = "Servicio para borrar un paciente por su ID usando generics.")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
	@DeleteMapping("/borrarPacienteG/{idPaciente}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void borrarPacienteG(@PathVariable Integer idPaciente) {
		patientService.deleteById(idPaciente);
	}

	// COUNT (GENERIC)
	@Operation(summary = "Cantidad de pacientes", description = "Servicio para obtener el numero total de pacientes del sistema.")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
	@GetMapping("/count")
	@ResponseStatus(code = HttpStatus.OK)
	public Long count() {
		return patientService.count();
	}

}