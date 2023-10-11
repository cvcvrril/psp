package model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@XmlRootElement(name = "orders")
@Data
public class OrdersXML {

    /*Atb*/

    private List<OrderXML> orderXML;

    /*Builder*/


    public OrdersXML(List<OrderXML> orderXML) {
        this.orderXML = orderXML;
    }
}
