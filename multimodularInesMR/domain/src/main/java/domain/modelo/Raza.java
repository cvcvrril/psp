package domain.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Raza {

    private int id;
    private String nombreRaza;
    private String planetaOrigen;

}
