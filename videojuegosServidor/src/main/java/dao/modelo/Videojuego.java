package dao.modelo;


import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder({"id", "titulo", "descripcion"})
public class Videojuego {

    private int id;
    private String titulo;
    private String descripcion;

}
