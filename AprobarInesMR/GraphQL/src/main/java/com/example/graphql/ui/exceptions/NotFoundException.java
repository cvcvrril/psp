package com.example.graphql.ui.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
       super("No se encontro el recurso.");
    }
}
