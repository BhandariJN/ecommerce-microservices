package com.jn.ecommerce.orderline;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;

    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLine) {
        var order = orderLineMapper.toEntity(orderLine);
        return orderLineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .toList();
    }
}
