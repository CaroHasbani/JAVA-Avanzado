package com.example.demo.repository;


import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findAll();
}