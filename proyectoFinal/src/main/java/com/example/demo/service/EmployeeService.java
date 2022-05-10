package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Rol;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.RolRepository;
import com.example.demo.responseDTO.EmployeeDTO;
import com.example.demo.responseDTO.RolDTO;


@Service
public class EmployeeService  {

	private EmployeeRepository employeeRepository;

	private RolRepository rolRepository;
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, RolRepository rolRepository,PasswordEncoder passwordEncoder) {
		this.employeeRepository = employeeRepository;
		this.rolRepository = rolRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<EmployeeDTO> getEmployees() {
		return employeeRepository.findAll().stream().map(EmployeeDTO::new).collect(Collectors.toList());
	}

	public RolDTO saveRol(Rol rol) {
		return new RolDTO(rolRepository.save(rol));
	}

	public EmployeeDTO saveEmployee(Employee employee) {
		if (employee.getRol() == null)
			employee.setRol(new ArrayList<Rol>());
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		return new EmployeeDTO(employeeRepository.save(employee));
	}

	public EmployeeDTO addRolToEmployee(String fullName, String rolName) {
		Employee employee = employeeRepository.findByFullName(fullName).orElse(null);
		Rol rol = rolRepository.findByRol(rolName).orElse(null);

		if (employee == null || rol == null)
			return null;

		if (rol.getUser() != null)
			rol = rolRepository.save(new Rol(rolName));

		rol.setUser(employee);
		employee = employeeRepository.save(employee);
		EmployeeDTO employeeDTO = new EmployeeDTO(employee);
		return employeeDTO;
	}

	public boolean deleteEmployee(String fullName) {
		if (employeeRepository.findById(fullName).isEmpty())
			return false;
		employeeRepository.deleteById(fullName);
		return true;
	}

}