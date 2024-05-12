package com.example.restservice.domain;

import com.example.restservice.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookH2Service {
    String jdbcURL = "jdbc:h2:mem:testdb"; // Connect to an in-memory database named "test"
    String username = "sa"; // Default username
    String password = "password"; // Default password (empty)

    /**
     * establish a connection to h2, add a book to the database
     * @param book
     * @return
     */
    public boolean addBook(Book book)  {
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement stmt = conn.createStatement()) {
            // Create a table
            createTable(stmt);
            // Insert a row
            String insertSql = "INSERT INTO books (id, title, author, publicationYear) VALUES ("+
                    String.valueOf(book.getId())+", '"+ book.getTitle()+"', '"+book.getAuthor()+"', "+String.valueOf(book.getPublicationYear())+")";
            stmt.execute(insertSql);
            return true;
        } catch (Exception e) {
            System.out.print(e.getMessage() +" " +e.getStackTrace());
            return false;
        }
    }

    /**
     * establish a connection to h2, retrieve book by id from the database
     * returns null if nothing is found in the table
     * @param id
     * @return
     */
    public Book retrieveBook(int id) {
        String retrieveByIDSQL = "SELECT * FROM books WHERE id = " + id+";";
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement statement = conn.createStatement();){
            //create Table
            createTable(statement);
            ResultSet resultSet = statement.executeQuery(retrieveByIDSQL);
            resultSet.next();
            id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            int publicationYear = resultSet.getInt("publicationYear");
            return new Book(id, title, author, publicationYear);
        } catch (Exception e) {
            System.out.print(e.getMessage() +" " + e.getStackTrace());
            return null;
        }
    }

    /**
     * establish a connection to h2 and retrieve all books from the database
     * retruns empty list if nothing is found in the database.
     * @return
     */
    public List<Book> retrieveAllBooks() {
        String retrieveAllSql = "SELECT * FROM books;";
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement statement = conn.createStatement();) {
            // Create a table
            createTable(statement);
            ResultSet resultSet = statement.executeQuery(retrieveAllSql);
            List<Book> books = new ArrayList();
            while (resultSet.next()) {
                // Process each row here
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int publicationYear = resultSet.getInt("publicationYear");
                books.add(new Book(id, title, author, publicationYear));
            }
            return books;
        } catch (SQLException e) {
            System.out.print(e.getMessage() +" " +e.getStackTrace());
            return new ArrayList<Book>();
        }
    }

    /**
     * establish a connection to h2 and delete a book by id from the database
     * @param id
     * @return
     */
    public boolean deleteBook(int id) {
        String deleteBookSQL = "DELETE FROM books WHERE id = "+id+";";
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement statement = conn.createStatement();) {
            createTable(statement);
            statement.execute(deleteBookSQL);
            return true;
        } catch (SQLException e) {
            System.out.print(e.getMessage() +" " +e.getStackTrace());
            return false;
        }
    }

    /**
     * establish a connection to h2 and update a book by id
     * @param id
     * @param title
     * @param author
     * @param publicationYear
     * @return
     */
    public boolean updateBook(int id, String title, String author, int publicationYear) {
        String updateBookSQL = "UPDATE books SET title = '"+title+"', author = '"+author+"', publicationYear = " +publicationYear +" WHERE id =" +id + ";";
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement statement = conn.createStatement();) {
            createTable(statement);
            statement.executeUpdate(updateBookSQL);
            return true;
        } catch (SQLException e) {
            System.out.print(e.getMessage() +" " +e.getStackTrace());
            return false;
        }
    }

    /**
     * creates a table if that table does not already exist
     * @param statement
     */
    private void createTable(Statement statement) throws SQLException{
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS books (id INT PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), publicationYear INT)");
        } catch (SQLException e) {
            throw e; //calling method will print the error
        }
    }
}
