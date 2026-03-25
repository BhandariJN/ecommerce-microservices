package com.jn.ecommerce.order;

import java.math.BigDecimal;
import java.util.List;

import com.jn.ecommerce.product.PurchaseRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Amount must be positive") BigDecimal amount,
        @NotNull(message = "Payment method is required") PaymentMethod paymentMethod,
        @NotNull(message = "Customer ID is required") @NotEmpty(message = "Customer ID cannot be empty") @NotBlank(message = "Customer ID cannot be blank") String customerId,
        @NotEmpty(message = "You Should at least buy one product") List<PurchaseRequest> products) {

}
