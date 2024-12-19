package com.polarshop.catalogservice;

import com.polarshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {
    private static final String URI_BOOKS = "/books";

    @Autowired
    private WebTestClient webClient;

    @Test
    @Disabled("no books in inmemory db")
    void whenGetAllBooksThenReturnCollectionOfBooks() {
        var book = new Book("1231231231", "Title", "Author", 9.90);

        webClient.get()
                .uri(URI_BOOKS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class).hasSize(1).contains(book);
    }

    @Test
    void shouldAddNewBookWhenRequestValid() {
        var book = new Book("1231231231", "Title", "Author", 9.90);
        webClient.post()
                .uri(URI_BOOKS)
                .bodyValue(book)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).isEqualTo(book);
    }


}
