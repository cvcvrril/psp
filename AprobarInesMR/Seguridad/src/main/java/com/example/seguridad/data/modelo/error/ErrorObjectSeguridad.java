package com.example.seguridad.data.modelo.error;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorObjectSeguridad {
    private String message;
    private LocalDateTime fecha;
}
