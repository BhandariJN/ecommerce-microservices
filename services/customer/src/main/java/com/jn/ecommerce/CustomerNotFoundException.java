package com.jn.ecommerce;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String customerNotFound) {
        super(customerNotFound);
    }
}
