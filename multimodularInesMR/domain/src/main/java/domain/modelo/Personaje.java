package domain.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personaje {
    private int id;
    private String nombre;
    private int raza;
    private String planetaRes;
    private List<Faccion> facciones;
}
