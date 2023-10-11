package model;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "order_item")
@Data
public class OrderItemXML {

    /*Atb*/
    @XmlElement(name = "menu_item")
    private String menuItem;
    @XmlElement(name = "quantity")
    private int quantity;

    /*Builder*/

    public OrderItemXML(String menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }
}
