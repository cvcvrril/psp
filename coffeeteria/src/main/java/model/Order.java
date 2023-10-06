package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class Order {

    /*Atributos*/

    private int id_co;
    private int id_ord;
    private int id_table;
    private LocalDate or_date;

    /*Construcctores*/


    public String toStringFile() {
        return id_co + ";" + id_ord + ";" + id_table + ";" + or_date + "\n";
    }
}
