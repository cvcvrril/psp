package dao.modelo;


import dao.ConstantsDao;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder({ConstantsDao.ID, ConstantsDao.NOMBRE, ConstantsDao.DESCRIPCION, ConstantsDao.ID_VIDEOJUEGO})
public class Personaje {
    private int id;
    private String nombre;
    private String descripcion;
    private int idVideojuego;


}
