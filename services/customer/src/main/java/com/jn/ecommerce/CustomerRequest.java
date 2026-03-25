package com.jn.ecommerce;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest
        (

                String id,
                @NotNull(message = "Customer First name is required")
                String firstName,

                @NotNull(message = "Customer Last name is required")
                String lastName,
                @NotNull(message = "Customer email is required")
                @Email(message = "Customer email should be valid")
                String email,

                Address address

        ) {
}
