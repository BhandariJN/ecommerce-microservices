package com.jn.notification.email;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html","Payment Sucessfully Processed"),

    ORDER_CONFIRMATION("order-confirmation.html","Order Confirmation");

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }


}
