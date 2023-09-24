package services;

import dao.imp.DAOclientsIMP;
import io.vavr.control.Either;
import model.Client;
import model.errors.ErrorC;

import java.util.List;

public class SERVclient {

    /*Atributos*/

    private final DAOclientsIMP daOclientsIMP;

    private ErrorC errorC;

    /*Constructor*/

    public SERVclient(DAOclientsIMP daOclientsIMP) {
        this.daOclientsIMP = daOclientsIMP;
    }

    /*Métodos*/

    public List<Client> getClients() {
        return daOclientsIMP.getClients();
    }

    public Either<ErrorC, List<Client>> getClients(int i) {
        if (i > 0)
            return Either.right(daOclientsIMP.getClients());
        else
            return Either.left(errorC);
    }

    public Client saveClient(int i) {
        return null;
    }

    public Client updateClient(int i) {
        return null;
    }

    public Client delClient(int i) {
        return null;
    }


}
