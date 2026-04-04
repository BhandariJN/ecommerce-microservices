package com.jn.ecommerce.notification;

import com.jn.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(

        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail


) {
}
