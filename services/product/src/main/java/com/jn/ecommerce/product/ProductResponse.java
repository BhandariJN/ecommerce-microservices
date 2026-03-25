package com.jn.ecommerce.product;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        double availableQuantity,
        Integer categoryId,
        String categoryName
) {
    }
