package com.example.restservice;

import com.example.restservice.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration End-to-End test for Books API.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class BookControllerIntegrationTest {

    //Test Data:
    public static final int EXPECTED_DON_QUIXOTE_ID = 1;
    public static final String DON_QUIXOTE_TITLE = "Don Quixote";
    public static final String DON_QUIXOTE_AUTHOR = "Miguel De Cervantes";
    public static final int DON_QUIXOTE_PUBLICATION_YEAR = 1605;

    public static final int EXPECTED_FRANKENSTEIN_ID = 2;
    public static final String FRANKENSTEIN_TITLE = "Frankenstein";
    public static final String FRANKENSTEIN_AUTHOR = "Marry Shelley";
    public static final int FRANKENSTEIN_PUBLICATION_YEAR = 1818;
    public static final String FRANKENSTEIN_UPDATE_TITLE = "DR FRANKENSTEIN";
    @Test
    public void exerciseAPIs() {
        BookController bookController =  new BookController();
        try {
            //add Don Quixote to database, expect success = true
            boolean success = bookController.createBook(DON_QUIXOTE_TITLE, DON_QUIXOTE_AUTHOR, DON_QUIXOTE_PUBLICATION_YEAR);
            assertTrue(success);

            //add Frankenstein to database, expect success = true
            success = bookController.createBook(FRANKENSTEIN_TITLE, FRANKENSTEIN_AUTHOR, FRANKENSTEIN_PUBLICATION_YEAR);
            assertTrue(success);

            //retrieve Don Quixote by expected id 1 and assert values are as expected
            Book donQuixote = bookController.retrieveBook(EXPECTED_DON_QUIXOTE_ID);
            assertBook(donQuixote, DON_QUIXOTE_TITLE, DON_QUIXOTE_AUTHOR, DON_QUIXOTE_PUBLICATION_YEAR);

            //retrieve Frankenstein by expected id 2 and assert values are as expected
            Book frankenstein = bookController.retrieveBook(EXPECTED_FRANKENSTEIN_ID);
            assertBook(frankenstein, FRANKENSTEIN_TITLE, FRANKENSTEIN_AUTHOR, FRANKENSTEIN_PUBLICATION_YEAR);

            //retrieveAllBooks, expect 2 in the list, verify contents of list is correct
            List<Book> books = bookController.retrieveAllBooks();
            assertEquals(2, books.size());
            assertBook(books.get(0), DON_QUIXOTE_TITLE, DON_QUIXOTE_AUTHOR, DON_QUIXOTE_PUBLICATION_YEAR);
            assertBook(books.get(1), FRANKENSTEIN_TITLE, FRANKENSTEIN_AUTHOR, FRANKENSTEIN_PUBLICATION_YEAR);

            //update Frankenstein, assert Expected values
            success = bookController.updateBook(EXPECTED_FRANKENSTEIN_ID, FRANKENSTEIN_UPDATE_TITLE, FRANKENSTEIN_AUTHOR, FRANKENSTEIN_PUBLICATION_YEAR);
            assertTrue(success);
            books = bookController.retrieveAllBooks();
            assertEquals(2, books.size());
            frankenstein = bookController.retrieveBook(EXPECTED_FRANKENSTEIN_ID);
            assertBook(frankenstein, FRANKENSTEIN_UPDATE_TITLE, FRANKENSTEIN_AUTHOR, FRANKENSTEIN_PUBLICATION_YEAR);

            //delete Don Quixote, assert 1 in retrieve all list, verify contents
            success = bookController.deleteBook(EXPECTED_DON_QUIXOTE_ID);
            assertTrue(success);
            books = bookController.retrieveAllBooks();
            assertEquals(1, books.size());
            frankenstein = bookController.retrieveBook(EXPECTED_FRANKENSTEIN_ID);
            assertBook(frankenstein, FRANKENSTEIN_UPDATE_TITLE, FRANKENSTEIN_AUTHOR, FRANKENSTEIN_PUBLICATION_YEAR);
        } catch (Exception e){
            fail("unexpected Exception: " + e.getMessage());
        }
    }

    private void assertBook(Book book, String title, String author, int publicationYear) {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(publicationYear, book.getPublicationYear());
    }
}