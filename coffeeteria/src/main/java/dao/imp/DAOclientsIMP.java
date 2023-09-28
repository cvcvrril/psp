package dao.imp;

import dao.DAOclients;
import io.vavr.control.Either;
import model.Client;
import model.Order;
import model.errors.ErrorC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DAOclientsIMP implements DAOclients {

    public List<Client>clientsList(){
        Either<ErrorC, List<Client>> res;
        List<Client> resClientsList = new ArrayList<>();
        resClientsList.add(new Client(1, "Pepe", "Pepito", "pepepepito@gmail.com", 123456789));
        resClientsList.add(new Client(2, "Juan", "Juanito", "juanjuanito@gmail.com", 987654321));
        resClientsList.add(new Client(3, "Lola", "Lolita", "lolalolita@gmail.com", 111111111));

        if (clientsList().isEmpty()) {
            Either.left(new ErrorC());
            return Collections.emptyList();
        }
            else {
            Either.right(resClientsList);
            return resClientsList;
        }

    }

    private static final List<Client> clients;

    static {
        clients = new ArrayList<>();
        clients.add(new Client(1, "Pepe", "Pepito", "pepepepito@gmail.com", 123456789));
        clients.add(new Client(2, "Juan", "Juanito", "juanjuanito@gmail.com", 987654321));
        clients.add(new Client(3, "Lola", "Lolita", "lolalolita@gmail.com", 111111111));

    }

    public List<Client> getClients() {
        //return clientsList();
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

