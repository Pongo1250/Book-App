package com.example.restservice;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.example.restservice.domain.BookH2Service;
import com.example.restservice.model.Book;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class BookController {
    BookH2Service h2Service = new BookH2Service();

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/createBook")
    public boolean createBook(@RequestParam(value = "title") String title,
                               @RequestParam(value = "author") String author,
                               @RequestParam(value = "publicationYear") int publicationYear) {
        List<Book> books = retrieveAllBooks();
        int newBookID =  books.size() + 1;
        Book newBookEntry = new Book(newBookID, title, author, publicationYear);
        return h2Service.addBook(newBookEntry);
    }

    @GetMapping("/retrieveAllBooks")
    public List<Book> retrieveAllBooks() {
        return h2Service.retrieveAllBooks();
    }
    @GetMapping("/deleteBook")
    public boolean deleteBook(@RequestParam(value = "id") int id) {
        return h2Service.deleteBook(id);
    }

    @GetMapping("/updateBook")
    public boolean updateBook(@RequestParam(value = "id") int id,
                              @RequestParam(value = "title") String title,
                              @RequestParam(value = "author") String author,
                              @RequestParam(value = "publicationYear") int publicationYear) {
        return h2Service.updateBook(id, title, author, publicationYear);
    }

    @GetMapping("/retrieveBook")
    public Book retrieveBook(@RequestParam(value = "id") int id) {
        return h2Service.retrieveBook(id);
    }

}
