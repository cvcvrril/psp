package dao.imp;

import dao.DAOclients;
import model.Client;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class DAOclientsIMP implements DAOclients {

    private static List<Client> getClients;

    static{
            getClients = new ArrayList<>();
            getClients.add(new Client(1, "Pepe", "Pepito"));
            getClients.add(new Client(2, "Juan", "Juanito"));
            getClients.add(new Client(3, "Lola", "Lolita"));

         }

    @Override
    public int save(Client t) {
        return 0;
    }

    @Override
    public int uptdate(Client t) {
        return 0;
    }

    @Override
    public int delete(Client t) {
        return 0;
    }
}

