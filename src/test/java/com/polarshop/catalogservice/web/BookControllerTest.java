package com.polarshop.catalogservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polarshop.catalogservice.domain.Book;
import com.polarshop.catalogservice.domain.BookService;
import com.polarshop.catalogservice.exception.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BookService bookService;

    @Test
    void whenGetBookByIsbnNotExistThenThrowNotFoundException() throws Exception {
        // GIVEN
        Mockito.when(bookService.viewBookDetails("1234567890")).thenThrow(BookNotFoundException.class);
        // WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1234567890"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void whenGetBookByIsbnExistThenReturnBook() throws Exception {
        // GIVEN
        Book book = new Book("1234567890", "White Wedding", "Billy Idol", 29.99);
        Mockito.when(bookService.viewBookDetails("1234567890")).thenReturn(book);

        // WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1234567890"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(book)));
    }

}