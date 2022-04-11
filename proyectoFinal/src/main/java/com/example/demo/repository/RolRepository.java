package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Rol;

public interface RolRepository extends CrudRepository<Rol, Integer> {
}