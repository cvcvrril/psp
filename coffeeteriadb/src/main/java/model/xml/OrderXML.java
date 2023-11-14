package model.xml;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
public class OrderXML {

    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "order_item")
    private List<OrderItemXML> orderItem;
}
