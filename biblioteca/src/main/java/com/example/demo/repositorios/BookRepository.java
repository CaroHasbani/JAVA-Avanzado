package com.example.demo.repositorios;
import com.example.demo.entidades.*;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {
}