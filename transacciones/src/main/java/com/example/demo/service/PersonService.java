package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person addPerson(Person person){
        return personRepository.save(person);
    }

    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    public List<Person> addPeople(List<Person> people){
        return people.stream()
                .map(person -> {
                    try{
                        return personRepository.save(person);
                    } catch (Exception e){
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Alias cannot be void");
                    }
                }).collect(Collectors.toList());
    }
}