package domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private int id;
    private String nombre;
    private List<Cancion> cancionList;
    private int artistaId;
}
