package services;

import dao.imp.DAOclientsIMP;
import model.Client;

import java.util.List;

public class SERVclient {

    /*Atributos*/

    private final DAOclientsIMP daOclientsIMP;

    /*Constructor*/

    public SERVclient(DAOclientsIMP daOclientsIMP) {
        this.daOclientsIMP = daOclientsIMP;
    }

    /*Métodos*/

    public List<Client> getCustomers(){
        return daOclientsIMP.getClients();
    }






}
