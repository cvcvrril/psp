package dao.modelo;


import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder({"id", "nombre", "descripcion", "idVideojuego"})
public class Personaje {
    private int id;
    private String nombre;
    private String descripcion;
    private int idVideojuego;


}
