package dao.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MenuItem {

    private int id;
    private String nameItem;
    private String description;
    private float price;

}
