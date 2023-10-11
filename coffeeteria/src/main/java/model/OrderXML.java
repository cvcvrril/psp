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
    private int idOrd;
    @XmlElement(name = "order_item")
    private List<String> orderItem;

    /*Constructor*/

    public OrderXML(int idOrd, List<String> orderItem) {
        this.idOrd = idOrd;
        this.orderItem = orderItem;
    }

    /*Métodos*/

}
