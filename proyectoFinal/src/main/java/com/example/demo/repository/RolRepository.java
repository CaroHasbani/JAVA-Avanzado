package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Rol;

public interface RolRepository extends CrudRepository<Rol, Integer> {

	Optional<Rol> findByRol(String rolName);
}