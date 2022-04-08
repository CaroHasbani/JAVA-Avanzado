package com.example.demo.implementation.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.generic.GenericRepository;
import com.example.demo.implementation.entity.Employee;

import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends GenericRepository<Employee, String> {
}