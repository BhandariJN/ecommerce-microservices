package com.jn.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

    @Async
    public void sendPaymentNotification(PaymentNotificationRequest request) {
        try {
            log.info("Sending Notification with Body <{}>", request);
            Message<PaymentNotificationRequest> message = MessageBuilder.withPayload(request)
                    .setHeader(TOPIC, "payment-topic")
                    .build();
            kafkaTemplate.send(message);
        } catch (Exception ex) {
            log.error("Failed to publish payment notification for orderReference={}", request.orderReference(), ex);
        }
    }
}
