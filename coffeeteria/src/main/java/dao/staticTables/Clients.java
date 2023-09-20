package dao.staticTables;

import model.Client;

import java.util.ArrayList;
import java.util.List;

public class Clients {

    /*Atributos*/
    private final List<Client> clientList;

    /*Constructores*/

    public Clients(List<Client> clientList) {
        this.clientList = new ArrayList<>();
        clientList.add(new Client(1, "Pepe", "Algo"));

    }
}
