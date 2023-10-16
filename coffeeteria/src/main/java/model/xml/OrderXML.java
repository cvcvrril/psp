package model.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderXML {

    /*Atb*/

    @XmlElement
    private int id;
    @XmlElement(name = "order_item")
    private List<OrderItemXML> orderItem;

    /*Builder*/

    public OrderXML(int id, List<OrderItemXML> orderItem) {
        this.id = id;
        this.orderItem = orderItem;
    }
}
