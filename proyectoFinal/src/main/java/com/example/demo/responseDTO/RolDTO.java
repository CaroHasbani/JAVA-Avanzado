package com.example.demo.responseDTO;
import com.example.demo.entity.Rol;

public class RolDTO {
	
	private String rol;
	
	public RolDTO(Rol rol) {
		this.rol = rol.getRol();
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}