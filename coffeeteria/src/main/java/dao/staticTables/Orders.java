package dao.staticTables;

import model.Client;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class Orders {

    /*Atributos*/
    private final List<Order> orderList;

    /*Constructores*/

    public Orders(List<Client> clientList) {
        this.orderList = new ArrayList<>();
    }
}
