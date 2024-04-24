package com.example.booklibrary.repository;

import com.example.booklibrary.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}

