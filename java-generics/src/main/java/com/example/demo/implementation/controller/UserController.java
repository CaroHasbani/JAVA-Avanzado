package com.example.demo.implementation.controller;
import com.example.demo.generic.GenericController;
import com.example.demo.generic.GenericService;
import com.example.demo.implementation.entity.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends GenericController<User, Long> {
    protected UserController(GenericService<User, Long> genService) {
        super(genService);
    }
}