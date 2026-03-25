package com.jn.ecommerce.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer productId,
        String name,
        String description,
        Integer quantity,
        BigDecimal price) {
}
