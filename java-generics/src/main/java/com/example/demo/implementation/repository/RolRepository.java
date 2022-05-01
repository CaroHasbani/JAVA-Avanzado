package com.example.demo.implementation.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.generic.GenericRepository;
import com.example.demo.implementation.entity.Rol;

@Repository
public interface RolRepository extends GenericRepository<Rol, Integer> {
}