package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entity.OpResultEntity;

public interface OpResultRepository extends JpaRepository<OpResultEntity, Long> {
}