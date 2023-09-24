package dao.imp;

import dao.DAOclients;
import io.vavr.control.Either;
import model.Client;
import model.Order;
import model.errors.ErrorC;

import java.util.ArrayList;
import java.util.List;

public class DAOclientsIMP implements DAOclients {

    private static final List<Client> clients;

    static {
        clients = new ArrayList<>();
        clients.add(new Client(1, "Pepe", "Pepito"));
        clients.add(new Client(2, "Juan", "Juanito"));
        clients.add(new Client(3, "Lola", "Lolita"));

    }

    public List<Client> getClients() {
        return clients;
    }

    public Either<ErrorC, List<Client>> getClient() {
        return null;
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

