package com.example.graphql.ui.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
       super("no se encontro el recurso ");
    }
}
