package com.jn.ecommerce.orderline;

import org.springframework.stereotype.Service;

import com.jn.ecommerce.order.Order;

@Service
public class OrderLineMapper {

    public OrderLine toEntity(OrderLineRequest orderLine) {

        return OrderLine.builder()
                .id(orderLine.id())
                .order(Order.builder().id(orderLine.orderId()).build())
                .productId(orderLine.productId())
                .quantity(orderLine.quantity())
                .build();
    }

}
