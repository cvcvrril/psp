package com.example.graphql.ui.exceptions;

public class FailedTokenException extends RuntimeException{
    public FailedTokenException (String message){
        super("El token está caducado.");
    }


}
