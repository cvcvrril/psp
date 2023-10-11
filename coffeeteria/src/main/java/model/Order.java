package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Order {

    /*Atributos*/

    private int idCo;
    private int idOrd;
    private int idTable;
    private LocalDate orDate;

    /*Construcctores*/


    public String toStringFile() {
        return idCo + ";" + idOrd + ";" + idTable + ";" + orDate + "\n";
    }
}
