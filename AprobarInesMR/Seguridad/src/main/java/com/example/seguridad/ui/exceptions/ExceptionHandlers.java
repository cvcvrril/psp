package com.example.seguridad.ui.exceptions;

import com.example.seguridad.data.modelo.error.ErrorObjectSeguridad;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(NullObjectException.class)
    public ResponseEntity<ErrorObjectSeguridad> handleException(NullObjectException e) {
        ErrorObjectSeguridad apiError = new ErrorObjectSeguridad(e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }
}
