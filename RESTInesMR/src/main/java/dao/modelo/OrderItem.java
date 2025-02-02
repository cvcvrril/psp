package dao.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private int id;             //order_item_id
    private int orderId;        //order_id
    private int menuItem;       //menu_item_id
    private int quantity;       //quantity

}
