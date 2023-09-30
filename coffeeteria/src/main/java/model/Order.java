package model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Order {

    /*Atributos*/

    private int id_co;
    private int id_ord;
    private int id_table;
    private LocalDate or_date;

    /*Construcctores*/

    public Order(int id_co, int id_ord, int id_table, LocalDate or_date) {
        this.id_co = id_co;
        this.id_ord = id_ord;
        this.id_table = id_table;
        this.or_date = or_date;
    }


    public String toStringFile() {
        return id_co + ";" + id_ord + ";" + id_table + ";" + or_date;
    }
}
