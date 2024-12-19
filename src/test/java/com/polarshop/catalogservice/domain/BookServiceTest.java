package com.polarshop.catalogservice.domain;

import com.polarshop.catalogservice.exception.BookNotFoundException;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testViewBooksCollectionWhenBooksArePresent() {
        // GIVEN
        Book book = new Book("1234567890", "White Wedding", "Billy Idol", 29.99);
        Mockito.when(bookRepository.findAll())
                .thenReturn(List.of(book));
        // WHEN
        Collection<Book> books = bookService.viewBookList();
        // THEN
        Assertions.assertThat(books)
                .hasSize(1)
                .extracting(Book::isbn, Book::title, Book::author, Book::price)
                .contains(Tuple.tuple("1234567890", "White Wedding", "Billy Idol", 29.99));
    }

    @Test
    void testViewBooksWhenNoBooksAvailable() {
        // GIVEN
        Mockito.when(bookRepository.findAll())
                .thenReturn(new ArrayList<>());
        // WHEN
        Collection<Book> books = bookService.viewBookList();
        // THEN
        Assertions.assertThat(books).isEmpty();
    }

    @Test
    void testViewBookDetailsWhenBookIsAvailable() {
        // GIVEN
        Book target = new Book("1234567890", "White Wedding", "Billy Idol", 29.99);
        Mockito.when(bookRepository.findByIsbn("1234567890"))
                .thenReturn(Optional.of(target));
        // WHEN
        Book book = bookService.viewBookDetails("1234567890");
        // THEN
        assertAll(
                () -> assertDoesNotThrow(() -> bookService.viewBookDetails("1234567890")),
                () -> assertEquals("1234567890", book.isbn()),
                () -> assertEquals("White Wedding", book.title()),
                () -> assertEquals("Billy Idol", book.author()),
                () -> assertEquals(29.99, book.price())
        );
    }

    @Test
    void testViewBookDetailsWhenBookIsNotAvailable() {
        // GIVEN
        Mockito.when(bookRepository.findByIsbn("1234567890"))
                .thenReturn(Optional.empty());
        // WHEN
        BookNotFoundException bookNotFoundException =
                assertThrows(BookNotFoundException.class, () -> bookService.viewBookDetails("1234567890"));
        // THEN
        assertEquals("The book with ISBN 1234567890 was not found.", bookNotFoundException.getMessage());
    }
}