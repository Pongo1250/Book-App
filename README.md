# Book-App API

# About the app
This application uses java 17, spring-boot 3.2.5, maven, Junit,  and h2
to store and operate on book data. Each book has an id, title, author, and publicationYear.
id is the primary key for the database and is generated by the code. 

# How to Run the application
you can run the application by running mvn clean install
and then deploying the war file to a tomcat server. 
The context path configuration should be "/" in tomcat.
This file can be found in the target folder

# Data
- Book
  - id
  - title
  - author
  - publicationYear

# Endpoints and how to call them
- createBook - http://localhost:8080/book-app/createBook?title=Cat in the hat&author=Dr Seuss&publicationYear=1980
  - returns a boolean, true if the save was successful, and false if there was an error.
  - errors are printed to the system console.

- retrieveAllBooks - http://localhost:8080/book-app/retrieveAllBooks
  - returns a JSON list of books

- retrieveBook - http://localhost:8080/book-app/retrieveBook?id=1
  - returns a Book object in JSON format

- updateBook - http://localhost:8080/book-app/updateBook?id=2&title=Harry%20Potter&author=JK%20Rowling&publicationYear=2000
  - returns true if the update succeeded and false if there was an error.
  - errors are printed to the system console

- deleteBook - http://localhost:8080/book-app/deleteBook?id=2
  - returns true if the delete succeeded and false if there was an error.
  - errors are printed to the system console
