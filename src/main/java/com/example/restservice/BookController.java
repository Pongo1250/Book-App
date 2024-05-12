package com.example.restservice;

import java.util.List;

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

    /**
     * add a book to the database
     * @param title
     * @param author
     * @param publicationYear
     * @return
     */
    @GetMapping("/book-app/createBook")
    public boolean createBook(@RequestParam(value = "title") String title,
                               @RequestParam(value = "author") String author,
                               @RequestParam(value = "publicationYear") int publicationYear) {
        List<Book> books = retrieveAllBooks();
        int newBookID =  books.size() + 1;
        Book newBookEntry = new Book(newBookID, title, author, publicationYear);
        return h2Service.addBook(newBookEntry);
    }

    /**
     * retrieve all books from database
     * @return
     */
    @GetMapping("/book-app/retrieveAllBooks")
    public List<Book> retrieveAllBooks() {
        return h2Service.retrieveAllBooks();
    }

    /**
     * delete a book from the database using an id
     * @param id
     * @return
     */
    @GetMapping("/book-app/deleteBook")
    public boolean deleteBook(@RequestParam(value = "id") int id) {
        return h2Service.deleteBook(id);
    }

    /**
     * update a book in the database by id
     * @param id
     * @param title
     * @param author
     * @param publicationYear
     * @return
     */
    @GetMapping("/book-app/updateBook")
    public boolean updateBook(@RequestParam(value = "id") int id,
                              @RequestParam(value = "title") String title,
                              @RequestParam(value = "author") String author,
                              @RequestParam(value = "publicationYear") int publicationYear) {
        return h2Service.updateBook(id, title, author, publicationYear);
    }

    /**
     * retrieve a book from the database by id
     * @param id
     * @return
     */
    @GetMapping("/book-app/retrieveBook")
    public Book retrieveBook(@RequestParam(value = "id") int id) {
        return h2Service.retrieveBook(id);
    }

}
