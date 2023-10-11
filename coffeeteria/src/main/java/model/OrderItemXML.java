package model;


import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "order_item")
@Data
public class OrderItemXML {

    /*Atb*/

    private String menuItem;
    private int quantity;

    /*Builder*/

    public OrderItemXML(String menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }
}
