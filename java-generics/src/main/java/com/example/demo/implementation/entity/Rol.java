package com.example.demo.implementation.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.generic.GenericEntity;

@Entity
@Table(name = "roles")
public class Rol implements GenericEntity<Rol, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "user")
    private Employee user;

    @Column(name = "rol")
    private String rol;

    public Rol() {
    }

    public Rol(String rol){
        this.user = null;
        this.rol = rol;
    }

    public Rol(Employee user, String permisos) {
        this.user = user;
        this.rol = permisos;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public void create() {

    }

    @Override
    public void update(Rol newT) {

    }

    @Override
    public void delete() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }

    public String getRol() { return rol; }

    public void setRol(String rol) { this.rol = rol; }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", user=" + user +
                ", rol='" + rol + '\'' +
                '}';
    }
}