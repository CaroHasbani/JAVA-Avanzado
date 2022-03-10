package com.example.demo.entidades;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="author")
public class Author {

    @Id
    @Column(name="author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="nacionality")
    private String nacionality;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private List<Book> book;

    public Author(){}

    public Author(String name , String nacionality) {

        this.name = name;
        this.nacionality = nacionality;
        this.book = new ArrayList<Book>();
    }

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


    public String getNacionality() {
        return nacionality;
    }

    public void setNacionalidad(String nacionality) {
        this.nacionality = nacionality;
    }

    public List<Book> getBooks() {
        return book;
    }

    public void setBooks(List<Book> books) {
        this.book = books;
    }

    @Override
    public String toString() {
        return "Autthor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nacionality='" + nacionality + '\'' +'}';
    }
}
