package com.jn.ecommerce.kafka;

import com.jn.ecommerce.customer.CustomerResponse;
import com.jn.ecommerce.order.PaymentMethod;
import com.jn.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,
        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        CustomerResponse customer,

        List<PurchaseResponse> products


) {
}
