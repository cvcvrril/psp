package model;


import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

//@XmlRootElement
@Data
public class OrderXML {

    /*Atributos*/

    private int id_ord;
    private List<String> order_item;

    /*Constructor*/

    public OrderXML(int id_ord, List<String> order_item) {
        this.id_ord = id_ord;
        this.order_item = order_item;
    }

    /*Métodos*/

}
