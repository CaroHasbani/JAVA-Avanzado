package com.example.demo.implementation.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.generic.GenericRepository;
import com.example.demo.implementation.entity.User;

@Repository
public interface UserRepository extends GenericRepository<User, Long> {
}