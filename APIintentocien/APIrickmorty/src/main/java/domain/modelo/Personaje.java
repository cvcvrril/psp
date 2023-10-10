package domain.modelo;

import lombok.Data;

@Data
public class Personaje {

    /*Atributos*/

    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Lugar location;

}
