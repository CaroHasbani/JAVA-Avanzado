package com.example.demo.repositorios;
import com.example.demo.entidades.*;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository< Author,Long> {
}
