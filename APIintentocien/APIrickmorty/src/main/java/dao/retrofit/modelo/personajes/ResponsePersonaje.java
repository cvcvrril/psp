package dao.retrofit.modelo.personajes;

import domain.modelo.MiLugar;
import lombok.Data;

@Data
public class ResponsePersonaje {
    private final int id;
    private final String name;
    private final String status;
    private final String species;
    private final String type;
    private final String gender;
    private final MiLugar location;


}
