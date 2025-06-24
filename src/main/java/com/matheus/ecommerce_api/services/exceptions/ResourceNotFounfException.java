package com.matheus.ecommerce_api.services.exceptions;

public class ResourceNotFounfException extends RuntimeException {
    public ResourceNotFounfException(Object id) {
        super("Resource not found. ID: " + id + "");
    }
}
