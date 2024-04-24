package com.example.booklibrary.controller;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.model.Rental;
import com.example.booklibrary.service.BookService;
import com.example.booklibrary.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found with id: " + id));
    }
    @GetMapping("overdueBy/{overdueByDays}")
    public List<Rental> getOverDueRentals(@PathVariable Long days) {
        return rentalService.getOverdueRentals(days);

    }

    @GetMapping("books/{booksCurrentlyRented}")
    public List<Book> getRentedBooks() {
        return rentalService.getbooksCurrentlyRented();
    }

    @GetMapping("/{availableBooks}")
    public List<Book> getAvailableBooks() {
        return rentalService.getAllAvailableBooks();
    }



    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.saveOrUpdateRental(rental);
    }

    @PutMapping("/{id}")
    public Rental updateRental(@PathVariable Long id, @RequestBody Rental rentalDetails) {
        Rental rental = rentalService.getRentalById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found with id: " + id));
        rental.setBookId(rentalDetails.getBookId());
        rental.setRenterName(rentalDetails.getRenterName());
        rental.setRentalDate(rentalDetails.getRentalDate());
        rental.setReturnDate(rentalDetails.getReturnDate());
        return rentalService.saveOrUpdateRental(rental);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
    }
}

