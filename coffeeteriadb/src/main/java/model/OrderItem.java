package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    private int id;
    private int orderId;
    private String menuItem;
    private int quantity;

}
