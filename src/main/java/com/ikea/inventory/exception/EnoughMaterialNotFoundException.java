package com.ikea.inventory.exception;

public class EnoughMaterialNotFoundException extends RuntimeException{

    public EnoughMaterialNotFoundException(String message) {
        super(message);
    }
}
