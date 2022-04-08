package com.example.demo.implementation.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.generic.GenericEntity;

@Entity
@Table(name = "user")
public class User implements GenericEntity<User, Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean auth;

    public User() {
    }

    public User(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    @Override
    public void create(){
        this.auth = true;
    }

    @Override
    public void update(User usuario){
        this.name = usuario.getName();
    }

    @Override
    public void delete(){
        this.auth = false;
    }
}