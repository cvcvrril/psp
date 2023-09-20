package dao.staticTables;

import model.Client;
import model.Coffee;

import java.util.ArrayList;
import java.util.List;

public class Coffees {

    /*Atributos*/
    private final List<Coffee>coffeeList;

    /*Constructores*/

    public Coffees(List<Client> clientList) {
        this.coffeeList = new ArrayList<>();
    }
}
