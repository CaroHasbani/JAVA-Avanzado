package com.example.demo.generic;

public interface GenericEntity<T, ID> {
    ID getId();
    void create();
    void update(T newT);
    void delete();
}