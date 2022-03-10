package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.entidades.Author;
import com.example.demo.entidades.Book;
import com.example.demo.entidades.Category;
import com.example.demo.entidades.Publisher;
import com.example.demo.servicios.AuthorService;
import com.example.demo.servicios.BookService;
import com.example.demo.servicios.CategoryService;
import com.example.demo.servicios.PublisherService;



@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PublisherService publisherService;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	public void run(String... args) throws Exception {

	
		Category category1 = new Category("Action");
		Category category2 = new Category("Sci-fi");

		categoryService.saveCategory(category1);
		categoryService.saveCategory(category2);
;

		Author author1 = new Author("JKR", "British");
		Author author2 = new Author("Tolkien", "American");

		authorService.saveAuthor(author1);
		authorService.saveAuthor(author2);

		Publisher publisher1 = new Publisher("Roca");
		Publisher publisher2 = new Publisher("Alfaguara");

		publisherService.savePublisher(publisher1);
		publisherService.savePublisher(publisher2);


		Book book1 = new Book("Under the same star",author1,publisher1,category2);
		Book book2 = new Book("Harry Potter",author1,publisher2,category2);


		bookService.saveBook(book1);
		bookService.saveBook(book2);


		authorService.getAuthors().forEach(author -> System.out.println(author));
		publisherService.getPublishers().forEach(publisher -> System.out.println(publisher));
		categoryService.getCategorys().forEach(category-> System.out.println(category));
		bookService.getBooks().forEach(book-> System.out.println(book));

	}
}

