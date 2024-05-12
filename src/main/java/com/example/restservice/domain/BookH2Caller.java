package com.example.restservice.domain;

import com.example.restservice.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookH2Caller {
    String jdbcURL = "jdbc:h2:mem:testdb"; // Connect to an in-memory database named "test"
    String username = "sa"; // Default username
    String password = "password"; // Default password (empty)
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
            System.out.print(e.getMessage() +" " +e.getStackTrace());
            return null;
        }
    }

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



    private void createTable(Statement statement) {
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS books (id INT PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), publicationYear INT)");

        } catch (SQLException e) {
            System.out.print(e.getMessage() +" " +e.getStackTrace());
        }
    }
}
