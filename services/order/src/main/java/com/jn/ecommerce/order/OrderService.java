package com.jn.ecommerce.order;

import com.jn.ecommerce.exception.EntityNotFoundException;
import com.jn.ecommerce.kafka.OrderConfirmation;
import com.jn.ecommerce.kafka.OrderProducer;
import com.jn.ecommerce.payment.PaymentClient;
import com.jn.ecommerce.payment.PaymentRequest;
import org.springframework.stereotype.Service;

import com.jn.ecommerce.customer.CustomerClient;
import com.jn.ecommerce.exception.BusinessException;
import com.jn.ecommerce.orderline.OrderLine;
import com.jn.ecommerce.orderline.OrderLineRequest;
import com.jn.ecommerce.orderline.OrderLineService;
import com.jn.ecommerce.product.ProductClient;
import com.jn.ecommerce.product.PurchaseRequest;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;

    private final OrderLineService orderLineService;

    private final OrderMapper orderMapper;

    private final OrderProducer orderProducer;

    private final PaymentClient paymentClient;

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
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation --> notification -ms (Kafka)
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                customer,
                purchasedProducts
        ));
        return order.getId();
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    public OrderResponse getOrderById(Integer id) {
        return orderRepository.findById(id).map(
                        orderMapper::toResponse
                )
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order not found with id: %d", id)));
    }
}
