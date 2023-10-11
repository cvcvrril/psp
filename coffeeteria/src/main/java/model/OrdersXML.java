package model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "orders")
@Data
public class OrdersXML {

    /*Atb*/

    private OrderXML orderXML;

    /*Builder*/


    public OrdersXML(OrderXML orderXML) {
        this.orderXML = orderXML;
    }
}
