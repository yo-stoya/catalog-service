package com.polarbookshop.catalogservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public record Book(
        @NotBlank(message = "Book ISBN must be defined")
        @Pattern(
                regexp = "^([0-9]{10}|[0-9]{13})$",
                message = "Book ISBN must be in valid format"
        )
        String isbn,

        @NotBlank(message = "Book title must be defined")
        String title,

        @NotBlank(message = "Book author must be defined")
        String author,

        @Positive(message = "Book price must be greater than 0")
        double price
) {
}
