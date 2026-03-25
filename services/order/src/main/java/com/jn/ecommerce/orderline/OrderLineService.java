package com.jn.ecommerce.orderline;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;

    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLine) {
        var order = orderLineMapper.toEntity(orderLine);
        return orderLineRepository.save(order).getId();
    }

}
