package com.jn.notification.kafka;


import com.jn.notification.email.EmailService;
import com.jn.notification.kafka.order.OrderConfirmation;
import com.jn.notification.kafka.payment.PaymentConfirmation;
import com.jn.notification.notification.Notification;
import com.jn.notification.notification.NotificationRepository;
import com.jn.notification.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;


    @KafkaListener(topics = "payment-topic")
    public void paymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info(String.format("Consuming the message form payment topic:: %s", paymentConfirmation));

        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMED)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()

        );

        var customerName = String.format("%s %s", 
                paymentConfirmation.customerFirstName() != null ? paymentConfirmation.customerFirstName() : "", 
                paymentConfirmation.customerLastName() != null ? paymentConfirmation.customerLastName() : ""
        ).trim();
        var amount = paymentConfirmation.amount();
        var orderReference = paymentConfirmation.orderReference();

        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                amount,
                orderReference
        );

    //to do send email


    }


    @KafkaListener(topics = "order-topic")
    public void orderConfirmationNotification(OrderConfirmation orderConfirmation) {
        log.info(String.format("Consuming the message form payment topic:: %s", orderConfirmation));

        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_CONFIRMED)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()

        );

        //to do send email

        var customerName = String.format("%s %s", 
                orderConfirmation.customer().firstName() != null ? orderConfirmation.customer().firstName() : "", 
                orderConfirmation.customer().lastName() != null ? orderConfirmation.customer().lastName() : ""
        ).trim();
        var amount = orderConfirmation.totalAmount();
        var orderReference = orderConfirmation.orderReference();
        var customerEmail = orderConfirmation.customer().email();
        var products = orderConfirmation.products();

        emailService.sendOrderConfirmationEmail(customerEmail, customerName, amount, orderReference, products);


    }
}
