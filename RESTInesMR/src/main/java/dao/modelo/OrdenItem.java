package dao.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrdenItem {

    private int id;
    private int orderId;
    private int menuItem;
    private int quantity;
    private List<MenuItem> menuItems;


}
