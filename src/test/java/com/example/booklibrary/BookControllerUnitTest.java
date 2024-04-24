package com.example.booklibrary;

import com.example.booklibrary.controller.BookController;
import com.example.booklibrary.model.Book;
import com.example.booklibrary.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerUnitTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = Arrays.asList(
                new Book("To Kill a Mockingbird", "Harper Lee", "978-0061120084", 1960),
                new Book("1984", "George Orwell", "978-0451524935", 1949)
        );

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("To Kill a Mockingbird"))
                .andExpect(jsonPath("$[1].title").value("1984"));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book("To Kill a Mockingbird", "Harper Lee", "978-0061120084", 1960);
        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("To Kill a Mockingbird"))
                .andExpect(jsonPath("$.author").value("Harper Lee"));
    }

    // Add tests for createBook(), updateBook(), deleteBook(), etc.
}

