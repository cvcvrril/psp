package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
