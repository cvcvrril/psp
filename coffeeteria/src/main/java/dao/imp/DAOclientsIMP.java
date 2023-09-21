package dao.imp;

import dao.DAOclients;
import model.Client;

import java.util.ArrayList;
import java.util.List;

public class DAOclientsIMP implements DAOclients {

     List<Client> getClients(){

        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client(1, "Pepe", "Algo"));
        clientList.add(new Client(2, "Juan", "Juanito"));
        return clientList;
    }
}
