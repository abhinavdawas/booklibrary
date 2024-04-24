package com.example.booklibrary.service;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;



    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveOrUpdateBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBookByAuthor(String author) {
        List<Book> books = bookRepository.findAll();
        List<Book>availableBookByAuthor = new ArrayList<>();
        for (Book b: books){
            String temp = b.getAuthor();

            if (temp.equals(author)){
                availableBookByAuthor.add(b);
            }
        }
        return availableBookByAuthor;

    }
}

