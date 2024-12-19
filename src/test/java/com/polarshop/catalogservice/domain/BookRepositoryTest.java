package com.polarshop.catalogservice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {
//    static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:13-alpine");

    BookRepository bookRepository;

    @Test
    void shouldReturnCollectionOfBooksWhenFindAll() {

    }
}