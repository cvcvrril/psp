package org.example.springjavafx.data.modelo;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorObject {

    private String message;
    private LocalDateTime fecha;

}
