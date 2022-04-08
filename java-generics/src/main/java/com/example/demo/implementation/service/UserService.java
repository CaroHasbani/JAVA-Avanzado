package com.example.demo.implementation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.generic.GenericService;
import com.example.demo.implementation.entity.User;
import com.example.demo.implementation.repository.UserRepository;

@Service
public class UserService extends GenericService<User, Long> {
    @Autowired
    public UserService(UserRepository usuarioRepository){
        super(usuarioRepository);
    }
}