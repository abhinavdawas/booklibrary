package com.example.booklibrary.service;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.model.Rental;
import com.example.booklibrary.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private BookService bookService;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public Rental saveOrUpdateRental(Rental rental) {
        Optional<Rental> bookToBeRented = getRentalById(rental.getId());
        Boolean checkIfCanBeRented = isAvailable(bookToBeRented, rental);
        if(!checkIfCanBeRented){
            throw new RuntimeException("Book Already Rented");
        }
        return rentalRepository.save(rental);
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }
    private Boolean isAvailable(Optional<Rental>bookToBeRented, Rental rental){
        if (bookToBeRented.get().getReturnDate()!=null && bookToBeRented.get().getReturnDate().before(rental.getRentalDate())){
            return true;
        }
        return false;
    }
    private Boolean isAvailable(Optional<Rental>bookToBeRented){
        Calendar calendar = Calendar.getInstance();
        Date currentDateFromCalendar = calendar.getTime();
        if (bookToBeRented.get().getReturnDate()!=null && bookToBeRented.get().getReturnDate().before(currentDateFromCalendar)){
            return true;
        }
        return false;
    }
    public List<Book> getAllAvailableBooks() {
        List<Book> books = bookService.getAllBooks();
        List<Long>availableBookIds = new ArrayList<>();
        for (Book b: books){
            Long bookId = b.getId();
            Optional<Rental> rental = getRentalById(bookId);
            Boolean isBookAvailable = isAvailable(rental);
            if (isBookAvailable){
                availableBookIds.add(rental.get().getBookId());
            }
        }
        return books.stream().filter(b-> availableBookIds.contains(b)).collect(Collectors.toList());
    }
    public List<Rental> getOverdueRentals(Long days) {
        List<Book> books = bookService.getAllBooks();
        List<Rental>overdueRentals = new ArrayList<>();
        for (Book b: books){
            Long bookId = b.getId();
            Optional<Rental> rental = getRentalById(bookId);
            Boolean isBookAvailable = isAvailable(rental);
            if (!isBookAvailable){
                overdueRentals.add(rental.get());
            }
        }
        return overdueRentals;
    }

    public List<Book> getbooksCurrentlyRented() {
        List<Book> books = bookService.getAllBooks();
        List<Long>rentedBookIds = new ArrayList<>();
        for (Book b: books){
            Long bookId = b.getId();
            Optional<Rental> rental = getRentalById(bookId);
            Boolean rentedBooks = !isAvailable(rental);
            if (rentedBooks){
                rentedBookIds.add(rental.get().getBookId());
            }
        }
        return books.stream().filter(b-> rentedBookIds.contains(b)).collect(Collectors.toList());
    }
}

