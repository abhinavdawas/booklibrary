package com.example.booklibrary;

import com.example.booklibrary.controller.AuthorController;
import com.example.booklibrary.model.Author;
import com.example.booklibrary.service.AuthorService;
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
public class AuthorControllerUnitTest {

    @InjectMocks
    private AuthorController authorController;

    @Mock
    private AuthorService authorService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    public void testGetAllAuthors() throws Exception {
        List<Author> authors = Arrays.asList(
                new Author("Harper Lee", "American novelist"),
                new Author("George Orwell", "English novelist and essayist")
        );

        when(authorService.getAllAuthors()).thenReturn(authors);

        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Harper Lee"))
                .andExpect(jsonPath("$[1].name").value("George Orwell"));
    }

    @Test
    public void testGetAuthorById() throws Exception {
        Author author = new Author("Harper Lee", "American novelist");
        when(authorService.getAuthorById(1L)).thenReturn(Optional.of(author));

        mockMvc.perform(get("/api/authors/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Harper Lee"))
                .andExpect(jsonPath("$.biography").value("American novelist"));
    }

    // Add tests for createAuthor(), updateAuthor(), deleteAuthor(), etc.
}
