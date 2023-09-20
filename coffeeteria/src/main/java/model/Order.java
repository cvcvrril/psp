package model;

import lombok.Data;
@Data
public class Order {

    /*Atributos*/

    private int id_co;
    private int id_ord;
    private int id_table;

    /*Construcctores*/

    public Order(int id_co,int id_ord, int id_table) {
        this.id_co = id_co;
        this.id_ord = id_ord;
        this.id_table = id_table;
    }

    /*Métodos*/

}
