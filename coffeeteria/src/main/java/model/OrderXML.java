package model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@XmlRootElement(name = "order")
@Data
public class OrderXML {

    /*Atributos*/

    @XmlElement(name = "id")
    private int id_ord;
    @XmlElement(name = "order_item")
    private List<String> order_item;

    /*Constructor*/

    public OrderXML(int id_ord, List<String> order_item) {
        this.id_ord = id_ord;
        this.order_item = order_item;
    }

    /*Métodos*/

}
