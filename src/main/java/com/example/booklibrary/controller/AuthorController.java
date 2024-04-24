package com.example.booklibrary.controller;

import com.example.booklibrary.model.Author;
import com.example.booklibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.saveOrUpdateAuthor(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        Author author = authorService.getAuthorById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        author.setName(authorDetails.getName());
        author.setBiography(authorDetails.getBiography());
        return authorService.saveOrUpdateAuthor(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}

