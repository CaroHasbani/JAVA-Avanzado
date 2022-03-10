package com.example.demo.entidades;

import javax.persistence.*;


@Entity
@Table(name="book")
public class Book {

    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;
;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id")
    private Author author;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="publisher_id")
    private Publisher publisher;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    public Book(){}
    public Book(String title, Author author, Publisher publisher, Category category) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return title;
    }

    public void setTitulo(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getEditorial() {
        return publisher;
    }

    public void setEditorial(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + 
                ", author=" + author.toString() +
                ", publisher=" + publisher.toString() +
                ", category=" + category.toString() +
                '}';
    }
}
