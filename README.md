
# **Book Library Application**

The Book Library application is a Spring Boot project that provides RESTful endpoints to manage a library of books, authors, and book rentals. This README provides instructions on setting up the application, running it, and testing the endpoints.

## Features

CRUD operations for books and authors.

Create and retrieve book rentals.

Endpoints to retrieve books by author, books available for rent, and books currently rented.

Business logic to ensure the book availability and track overdue rentals.

Error handling and validation for invalid requests.

Unit tests to verify core business logic and RESTful endpoints.


### Setup Instructions

#### Prerequisites

Java Development Kit (JDK) version 8 or higher installed on your machine.

Apache Maven for building the project.

MySQL database server.

##### Database Configuration

Create a MySQL database for the Book Library application.

Update the application.properties file located in src/main/resources directory with your database connection details.

spring.datasource.url=jdbc:mysql://localhost:3306/book_library_db
spring.datasource.username=your_username
spring.datasource.password=your_password

##### Running the Application

Clone the repository to your local machine.

Navigate to the project directory.

Run the following command to build the project:**mvn clean install**

Run the following command to start the application: mvn spring-boot:run

The application will start on port 8080 by default.

#### Testing the Endpoints

You can test the RESTful endpoints using tools like cURL, Postman, or any HTTP client.

### Sample Requests

#### Retrieve all books: GET /api/books
#### Retrieve a book by ID: GET /api/books/{id}
#### Retrieve books by author: GET /api/books/byAuthor/{author}
#### Create a new book: POST /api/books
{
"title": "Book Title",
"author": "Author Name",
"isbn": "978-3-16-148410-0",
"publicationYear": 2022
}


#### Additional Endpoints

CRUD operations for authors are similar to books but with /api/authors endpoints.

Endpoints for book rentals are available under /api/rentals.


### Additional Documentation

For more information on the design decisions, assumptions made during development, and additional documentation, please refer to the codebase and comments within the project.














