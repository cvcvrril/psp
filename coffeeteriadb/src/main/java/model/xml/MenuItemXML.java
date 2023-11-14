package model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@XmlRootElement(name = "menu_item")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
public class MenuItemXML {

    @XmlElement(name = "name")
    private String menuItemName;

}
