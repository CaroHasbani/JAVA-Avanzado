package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

	Optional<Employee> findByFullName(String fullName);
}