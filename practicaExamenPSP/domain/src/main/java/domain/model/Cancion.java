package domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cancion {
    private int id;
    private String nombre;
    private List<Album> albumCancion; //Albumes que contienen esta canción

}
