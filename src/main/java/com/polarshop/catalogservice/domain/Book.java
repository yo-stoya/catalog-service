package com.polarshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(
        @NotBlank(message = "Book ISBN must be defined.")
        @Pattern(
                regexp = "^([0-9]{10}|[0-9]{13})$",
                message = "The ISBN format must be valid."
        )
        String isbn,

        @NotBlank(message = "Book title must be defined.")
        String title,

        @NotBlank(message = "Book author must be defined.")
        String author,

        @NotNull(message = "Book price must be defined.")
        @Positive(message = "Book price must be greater than zero")
        Double price
) {
}
