package com.jn.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "Name cannot be null")
        String name,
        @NotNull(message = "Last name cannot be null")
        String lastName,
        @NotNull(message = "Email cannot be null")
        @Email(message = "Email must be valid")
        String email
) {
}
