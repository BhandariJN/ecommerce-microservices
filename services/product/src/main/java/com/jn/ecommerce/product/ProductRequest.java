package com.jn.ecommerce.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Builder

public record ProductRequest(
        Integer id,
        @NotNull(message = "name is required")
        String name,
        @NotBlank(message = "description is required")
        String description,

        @NotNull(message = "available quantity is required")
        @Positive(message = "available quantity should be positive")
        Integer availableQuantity,

        @NotNull(message = "price is required")
        @PositiveOrZero(message = "price should be positive or zero")
        BigDecimal price,

        @NotNull(message = "category id is required")
        @Positive(message = "category id should be positive")
        Integer categoryId
) {
}
