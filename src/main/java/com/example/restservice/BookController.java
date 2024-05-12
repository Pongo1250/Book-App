package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.example.restservice.domain.BookH2Caller;
import com.example.restservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class BookController {
        BookH2Caller h2Caller = new BookH2Caller();

        private static final String template = "Hello, %s!";
        private final AtomicLong counter = new AtomicLong();

        @GetMapping("/createBook")
        public boolean createBook(@RequestParam(value = "title") String title,
                                   @RequestParam(value = "author") String author,
                                   @RequestParam(value = "publicationYear") int publicationYear) {
            List<Book> books = retrieveAllBooks();
            int newBookID =  books.size() + 1;
            Book newBookEntry = new Book(newBookID, title, author, publicationYear);
            return h2Caller.addBook(newBookEntry);
        }

        @GetMapping("/retrieveAllBooks")
        public List<Book> retrieveAllBooks() {
            return h2Caller.retrieveAllBooks();
        }

        @GetMapping("/retrieveBook")
        public Book retrieveBook(@RequestParam(value = "id") int id) {
            return h2Caller.retrieveBook(id);
        }

}
