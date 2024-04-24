package com.example.booklibrary;

import com.example.booklibrary.controller.RentalController;
import com.example.booklibrary.model.Rental;
import com.example.booklibrary.service.RentalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class RentalControllerUnitTest {

    @InjectMocks
    private RentalController rentalController;

    @Mock
    private RentalService rentalService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(rentalController).build();
    }

    @Test
    public void testGetAllRentals() throws Exception {
        List<Rental> rentals = Arrays.asList(
                new Rental(1L, 1L, "John Doe", new Date(), null),
                new Rental(2L, 2L, "Jane Smith", new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000), new Date(System.currentTimeMillis() - 14 * 24 * 60 * 60 * 1000))
        );

        when(rentalService.getAllRentals()).thenReturn(rentals);

        mockMvc.perform(get("/api/rentals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].renterName").value("John Doe"))
                .andExpect(jsonPath("$[1].renterName").value("Jane Smith"));
    }




}


