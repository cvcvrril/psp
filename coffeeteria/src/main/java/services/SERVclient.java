package services;

import dao.imp.DAOclientsFICHERO;
import dao.imp.DAOclientsIMP;
import io.vavr.control.Either;
import model.Client;
import model.errors.ErrorC;

import java.util.List;

public class SERVclient {

    /*Atributos*/

    private final DAOclientsIMP daOclientsIMP;
    private final DAOclientsFICHERO daOclientsFICHERO;

    private ErrorC errorC;

    /*Constructor*/

    public SERVclient(DAOclientsIMP daOclientsIMP, DAOclientsFICHERO daOclientsFICHERO) {
        this.daOclientsIMP = daOclientsIMP;
        this.daOclientsFICHERO = daOclientsFICHERO;
    }

    /*Métodos*/

    public Either<ErrorC, List<Client>> getClients() {
        //return daOclientsIMP.getClients();
        return daOclientsFICHERO.getAll();
    }

    public Either<ErrorC, Client> getClients(int i) {
            return daOclientsFICHERO.get(i);
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
