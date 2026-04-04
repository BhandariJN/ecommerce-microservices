package com.jn.notification.email;

import com.jn.notification.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender sender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(String destinationEmail, String customerFirstName, BigDecimal amount, String orderReference) {

        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED);
            helper.setFrom("notification@jn.com");
            helper.setTo(destinationEmail);
            helper.setSubject("Payment Success");

            final String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();

            Map<String, Object> variables = new HashMap<>();
            variables.put("customerFirstName", customerFirstName);
            variables.put("amount", amount);
            variables.put("orderReference", orderReference);

            Context context = new Context();
            context.setVariables(variables);

            helper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());

            String template = templateEngine.process(templateName, context);
            helper.setText(template, true);
            helper.setTo(destinationEmail);
            sender.send(message);
            log.info(String.format("Email Sucessfully Sent to %s with template %s", destinationEmail, templateName));
        } catch (MessagingException e) {
            log.warn("Can not Send Email to {}", destinationEmail);
        }
    }



    @Async
    public void sendOrderConfirmationEmail(String destinationEmail, String customerFirstName, BigDecimal amount, String orderReference,
                                           List<Product> products) {

        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED);
            helper.setFrom("notification@jn.com");
            helper.setTo(destinationEmail);
            helper.setSubject("Payment Success");

            final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();

            Map<String, Object> variables = new HashMap<>();
            variables.put("customerFirstName", customerFirstName);
            variables.put("totalAmount", amount);
            variables.put("orderReference", orderReference);
            variables.put("products", products);

            Context context = new Context();
            context.setVariables(variables);

            helper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

            String template = templateEngine.process(templateName, context);
            helper.setText(template, true);
            helper.setTo(destinationEmail);
            sender.send(message);
            log.info("Order confirmation email sent to {} for orderReference={} with template {}",
                    destinationEmail,
                    orderReference,
                    templateName);
        } catch (MessagingException e) {
            log.warn("Can not Send Email to {}", destinationEmail);
        }
    }



}
