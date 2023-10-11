package model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@XmlRootElement(name = "order")
@Data
public class OrderXML {

    /*Atb*/

    @XmlElement(name = "id")
    private int idOrd;
    @XmlElement(name = "order_item")
    private List<OrderItemXML> orderItem;

    /*Builder*/

    public OrderXML(int idOrd, List<OrderItemXML> orderItem) {
        this.idOrd = idOrd;
        this.orderItem = orderItem;
    }

}
