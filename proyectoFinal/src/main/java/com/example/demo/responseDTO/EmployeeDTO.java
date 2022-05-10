package com.example.demo.responseDTO;


import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Employee;



public class EmployeeDTO {
	
	private String fullName;
	private Boolean active;
	private List<RolDTO> roles; 
	
	public EmployeeDTO(Employee employee) {
		this.fullName = employee.getFullName();
		this.active = employee.isActive();
		this.roles = new ArrayList<>();
		employee.getRol().forEach(rol -> this.roles.add(new RolDTO(rol)));
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<RolDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RolDTO> roles) {
		this.roles = roles;
	}


	
	

}