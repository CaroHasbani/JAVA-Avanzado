package com.example.demo.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Rol;
import com.example.demo.responseDTO.EmployeeDTO;
import com.example.demo.responseDTO.RolDTO;
import com.example.demo.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee", description = "employee´s API")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Operation(summary = "Employee´s  list", description = "Service to get a list of all the employees.")
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {
		return ResponseEntity.ok().body(employeeService.getEmployees());
	}

	@Operation(summary = "Add employee", description = "Sevice to add employees")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Employee added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))) })
	@PostMapping("/employee")
	public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody Employee employee) {
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employee));
	}

	@Operation(summary = "Add Rol", description = "Service to add rol")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Rol added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RolDTO.class))) })
	@PostMapping("/rol")
	public ResponseEntity<RolDTO> saveRol(@RequestBody Rol rol) {
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveRol(rol));
	}

	@Operation(summary = "Add rol to employee", description = "Service to add a rol to an employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Rol added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json")) })
	@PutMapping("/employee/rol")
	public ResponseEntity<EmployeeDTO> setRol(@RequestParam String username, @RequestParam String rolName) {
		EmployeeDTO employeeDTO= employeeService.addRolToEmployee(username, rolName);
		return (employeeDTO!= null ? ResponseEntity.status(HttpStatus.CREATED).body(employeeDTO) : ResponseEntity.notFound().build());
	}

	@Operation(summary = "Delete employee", description = "Service to delete an employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Employee deleted successfully", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content(mediaType = "application/json")) })
	@DeleteMapping("/employee/{username}")
	public ResponseEntity<?> deleteEmployee(@PathVariable String username) {
		return (employeeService.deleteEmployee(username) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.notFound().build());
	}

}