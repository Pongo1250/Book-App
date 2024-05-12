package com.example.restservice.model;

/**
 * represents data for a Book
 */
public class Book {

    public Book (int id, String title, String author, int publicationYear) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setPublicationYear(publicationYear);
    }
    private int id;
    private String title;
    private String author;
    private int publicationYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}
