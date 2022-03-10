package com.example.demo.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Publisher;
import com.example.demo.repositorios.PublisherRepository;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public Iterable<Publisher> getPublishers(){return publisherRepository.findAll();}
    public void savePublisher(Publisher publisher){
        publisherRepository.save(publisher);
    }
}