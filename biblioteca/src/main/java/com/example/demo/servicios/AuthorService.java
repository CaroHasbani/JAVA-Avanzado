package com.example.demo.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entidades.Author;
import com.example.demo.repositorios.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Iterable<Author> getAuthors(){return  authorRepository.findAll();}

    public void saveAuthor(Author author){
        authorRepository.save(author);
    }

    public Author getAuthor(Long id){
       return authorRepository.findById(id).orElse(null);
    }

}
