package com.jn.notification.kafka.order;

import java.math.BigDecimal;

public record Order(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        Integer quantity

) {
}
