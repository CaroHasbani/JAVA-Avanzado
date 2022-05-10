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



@RestController
@RequestMapping("/api/signosVitales")
@Tag(name = "Vital Signs", description = "PAtient´s vital signs api")
public class VItalSignController {

	@Autowired
	private VitalSignService vitalSignService;

	// Muestra los signos vitales
	@Operation(summary = "Get vital signs", description = "Service to obtain the list of all vital signs.")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<VitalSignDTO> getVitalSigns() {
		return vitalSignService.getVitalSigns();
	}

	// Muestra los signos vitales del paciente
	@Operation(summary = "List all the vital signs of a specified patient", description = "Service to obtain the list of all the vital signs of a specified patient.")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@GetMapping("/patient")
	@ResponseStatus(code = HttpStatus.OK)
	public List<VitalSignDTO> getVitalSignsByPatient(@RequestBody Patient patient) {
		return vitalSignService.getVitalSignsByPatient(patient);
	}

	// Actualizar signos vitales

	@Operation(summary = "Update a vital sign", description = "Service to update a patient's vital sign.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Vital sign updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VitalSignDTO.class))),
			@ApiResponse(responseCode = "404", description = "VItal sign not found", content = @Content(mediaType = "application/json")) })
	@PreAuthorize("hasAuthority('SCOPE_ROLE_NURSE')")
	@PutMapping("/patient/{idSignoVital}")
	public ResponseEntity<List<VitalSignDTO>> actualizarSignosVitales(@RequestBody VitalSign vitalSign,
			@PathVariable Integer idSignoVital) {
		List<VitalSignDTO> signos = vitalSignService.updateVitalSigns(vitalSign, idSignoVital);
		return (signos != null) ? ResponseEntity.status(HttpStatus.CREATED).body(signos)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	// Eliminar signos vitales
	@Operation(summary = "Delete a vital sign", description = "Service to delete a patient's vital sign.")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_DOCTOR')")
	@DeleteMapping("/patient/{idSignoVital}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminarSignoVital(@PathVariable Integer idSignoVital) {
		vitalSignService.deleteVitalSign(idSignoVital);
	}
}