package com.jn.ecommerce.payment;

import com.jn.ecommerce.customer.CustomerResponse;
import com.jn.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

BigDecimal amount,
PaymentMethod paymentMethod,
Integer orderId,
String orderReference,
CustomerResponse customer
) {
}
