package services;

import dao.imp.DAOclientsFICHERO;
import dao.imp.DAOclientsIMP;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Client;
import model.errors.ErrorC;
import model.errors.ErrorCCustomer;

import java.util.List;

public class SERVclient {

    /*Atributos*/

    private final DAOclientsIMP daOclientsIMP;
    private final DAOclientsFICHERO daOclientsFICHERO;

    private ErrorC errorC;

    /*Constructor*/

    @Inject
    public SERVclient(DAOclientsIMP daOclientsIMP, DAOclientsFICHERO daOclientsFICHERO) {
        this.daOclientsIMP = daOclientsIMP;
        this.daOclientsFICHERO = daOclientsFICHERO;
    }

    /*Métodos*/

    public int getLastUsedId() {
        List<Client> clients = daOclientsFICHERO.getAll().getOrNull();
        int lastUsedId = 0;
        for (Client client : clients) {
            if (client.getId_c() > lastUsedId) {
                lastUsedId = client.getId_c();
            }
        }
        return lastUsedId + 1;
    }

    public Either<ErrorCCustomer, List<Client>> getClients() {
        //return daOclientsIMP.getClients();
        return daOclientsFICHERO.getAll();
    }

    public Either<ErrorCCustomer, Client> getClients(int i) {
            return daOclientsFICHERO.get(i);
    }

    public Either<ErrorCCustomer, Integer> saveClient(Client i) {
        return daOclientsFICHERO.save(i);
    }

    public Either<ErrorCCustomer, Integer> updateClient(int i) {
        return daOclientsFICHERO.save(getClients(i).getOrNull());
    }

    public Either<ErrorCCustomer, Integer> delClient(int i) {
        return daOclientsFICHERO.delete(getClients(i).getOrNull());
    }


}
