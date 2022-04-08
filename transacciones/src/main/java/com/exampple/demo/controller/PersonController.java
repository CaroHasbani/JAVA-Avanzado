package com.exampple.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/people")
    public List<Person> getPersons(){
        return personService.getPersons();
    }

    @PostMapping("/people/many")
    public List<Person> addPeople(@RequestBody List<Person> people){
        return personService.addPeople(people);
    }

    @PostMapping("/people")
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }
}