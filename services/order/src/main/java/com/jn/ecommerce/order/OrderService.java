package com.jn.ecommerce.order;

import org.springframework.stereotype.Service;

import com.jn.ecommerce.customer.CustomerClient;
import com.jn.ecommerce.exception.BusinessException;
import com.jn.ecommerce.orderline.OrderLine;
import com.jn.ecommerce.orderline.OrderLineRequest;
import com.jn.ecommerce.orderline.OrderLineService;
import com.jn.ecommerce.product.ProductClient;
import com.jn.ecommerce.product.PurchaseRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;

    private final OrderLineService orderLineService;

    private final OrderMapper orderMapper;

    public Integer createOrder(OrderRequest request) {
        // check the customer
        var customer = customerClient.findByCustomerId(request.customerId())
                .orElseThrow(() -> new BusinessException(
                        "Can not Create Order. No Customer with ID: " + request.customerId()));

        // purchase the products --> product ms (Rest Template)

        var purchasedProducts = productClient.purchaseProducts(request.products());

        // persist order

        // persist order lines
        var order = orderRepository.save(orderMapper.toEntity(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(

                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()));
        }

        // Start payment process

        // send the order confirmation --> notification -ms (Kafka)
        return null;
    }

}
