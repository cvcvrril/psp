package model.xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@XmlRootElement(name = "order_item")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
public class OrderItemXML {

    @XmlElement(name = "menu_item")
    private String menuItem;
    @XmlElement(name = "quantity")
    private int quantity;
}
