package com.jn.ecommerce.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String format) {
        super(format);
    }
}
