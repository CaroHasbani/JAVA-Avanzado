package com.example.demo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Book;
import com.example.demo.repositorios.BookRepository;



@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Iterable<Book> getBooks(){return bookRepository.findAll();    }

    public void saveBook(Book book){
        bookRepository.save(book);
    }
}
