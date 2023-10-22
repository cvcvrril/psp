package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Order {

    /*Atributos*/

    private int idOrd;
    private LocalDateTime orDate;
    private int idCo;
    private int idTable;


    /*Construcctores*/


    public String toStringFile() {
        return idCo + ";" + idOrd + ";" + idTable + ";" + orDate + "\n";
    }
}
