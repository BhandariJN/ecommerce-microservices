package com.jn.ecommerce.payment;

import com.jn.ecommerce.notification.NotificationProducer;
import com.jn.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final  PaymentRepository paymentRepository;
    private final  PaymentMapper mapper;
    private final NotificationProducer notificationProducer;


    public Integer createPayment( PaymentRequest request) {
        var payment = paymentRepository.save(mapper.mapToPayment(request));

        try {
            notificationProducer.sendPaymentNotification(new PaymentNotificationRequest(
                    request.orderReference(),
                    request.amount(),
                    request.paymentMethod(),
                    request.customer().name(),
                    request.customer().lastName(),
                    request.customer().email()
            ));
        } catch (Exception ex) {
            // Payment is already persisted; notification can be retried asynchronously.
            log.error("Payment created but notification publishing failed for orderReference={}", request.orderReference(), ex);
        }

        return payment.getId();


    }
}
