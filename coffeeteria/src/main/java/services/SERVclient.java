package services;

import dao.imp.DAOclientsFILE;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Client;
import model.errors.ErrorC;
import model.errors.ErrorCCustomer;

import java.util.List;

public class SERVclient {

    /*Atributos*/

    private final DAOclientsFILE daOclientsFILE;

    private ErrorC errorC;

    /*Constructor*/

    @Inject
    public SERVclient(DAOclientsFILE daOclientsFILE) {
        this.daOclientsFILE = daOclientsFILE;
    }

    /*Métodos*/

    public int getLastUsedId() {
        List<Client> clients = daOclientsFILE.getAll().getOrNull();
        int lastUsedId = 0;
        for (Client client : clients) {
            if (client.getId_c() > lastUsedId) {
                lastUsedId = client.getId_c();
            }
        }
        return lastUsedId + 1;
    }

    public Either<ErrorCCustomer, List<Client>> getClients() {
        return daOclientsFILE.getAll();
    }

    public Either<ErrorCCustomer, Client> getClients(int i) {
            return daOclientsFILE.get(i);
    }

    public Either<ErrorCCustomer, Integer> saveClient(Client i) {
        return daOclientsFILE.save(i);
    }

    public Either<ErrorCCustomer, Integer> updateClient(Client i) {
        return daOclientsFILE.update(i);
    }

    public Either<ErrorCCustomer, Integer> delClient(int i) {


        Either<ErrorCCustomer, Client> res = daOclientsFILE.get(i);
        if (res.isRight()){
            Client client = res.get();
            return daOclientsFILE.delete(client);
        }else {
            return Either.left(res.getLeft());
        }

    }
}
