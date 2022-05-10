package com.example.demo.controller;


import com.example.demo.entity.Patient;
import com.example.demo.entity.VitalSign;
import com.example.demo.responseDTO.VitalSignDTO;
import com.example.demo.service.VitalSignService;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
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

import java.util.List;

@RestController
@RequestMapping("/api/signosVitales")
@Tag(name = "Signos Vitales", description = "API de Signos Vitales del Patient")
public class VItalSignController {

	@Autowired
	private VitalSignService vitalSignService;

	// MUESTRA TODOS LOS SIGNOS VITALES REGISTRADOS DE TODOS LOS USUARIOS
	@Operation(summary = "Listar todos los signos vitales", description = "Servicio para obtener el listado de todos los signos vitales.")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<VitalSignDTO> listarSignosVitales() {
		returnvitalSignService.listarSignosVitales();
	}

	// MUESTRA LOS SIGNOS VITALES DE UN PACIENTE SOLICITADO
	@Operation(summary = "Listar todos los signos vitales de un patient especificado", description = "Servicio para obtener el listado de todos los signos vitales de un patient especificado.")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@GetMapping("/patient")
	@ResponseStatus(code = HttpStatus.OK)
	public List<VitalSignDTO> listarSignosVitalesPorPaciente(@RequestBody Patient patient) {
		return vitalSignService.listarSignosVitalesPorPaciente(patient);
	}

	// ACTUALIZA UN SIGNO VITAL Y DEVUELVE LA LISTA DE LOS SIGNOS VITALES DE ESE
	// PACIENTE
	@Operation(summary = "Actualizar un signo vital", description = "Servicio para actualizar un signo vital de un patient.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Signo vital actualizado satisfactoriamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VitalSignDTO.class))),
			@ApiResponse(responseCode = "404", description = "No se encontro el signo vital especificado especificado", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@PutMapping("/patient/{idSignoVital}")
	public ResponseEntity<List<VitalSignDTO>> actualizarSignosVitales(@RequestBody VitalSign vitalSign,
			@PathVariable Integer idSignoVital) {
		List<VitalSignDTO> signos = vitalSignService.actualizarSignosVitales(vitalSign, idSignoVital);
		return (signos != null) ? ResponseEntity.status(HttpStatus.CREATED).body(signos)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	// ELIMINA UN SIGNO VITAL DE UN PACIENTE
	@Operation(summary = "Borrar un signo vital", description = "Servicio para borrar un signo vital de un patient.")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
	@DeleteMapping("/patient/{idSignoVital}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminarSignoVital(@PathVariable Integer idSignoVital) {
		vitalSignService.eliminarSignoVital(idSignoVital);
	}
}