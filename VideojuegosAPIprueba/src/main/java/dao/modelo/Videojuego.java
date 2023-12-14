package dao.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videojuego {

    private int id;
    private String titulo;
    private LocalDateTime fecha;
    private String descripcion;


}
