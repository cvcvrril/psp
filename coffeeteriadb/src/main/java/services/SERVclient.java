package services;

import dao.db.DAOcustomerDB;
import dao.imp.DAOclientsFILE;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.errors.ErrorC;
import model.errors.ErrorCCustomer;

import java.util.List;

public class SERVclient {

    /*Atributos*/

    private final DAOclientsFILE daOclientsFILE;
    private final DAOcustomerDB daOcustomerDB;

    private ErrorC errorC;

    /*Constructor*/

    @Inject
    public SERVclient(DAOclientsFILE daOclientsFILE, DAOcustomerDB daOcustomerDB) {
        this.daOclientsFILE = daOclientsFILE;
        this.daOcustomerDB = daOcustomerDB;
    }

    /*Métodos*/

    public int getLastUsedId() {
        List<Customer> customers = daOclientsFILE.getAll().getOrNull();
        int lastUsedId = 0;
        for (Customer customer : customers) {
            if (customer.getIdC() > lastUsedId) {
                lastUsedId = customer.getIdC();
            }
        }
        return lastUsedId + 1;
    }

    public Either<ErrorCCustomer, List<Customer>> getClients() {
        return daOclientsFILE.getAll();
    }


    public Either<ErrorCCustomer, Customer> getClients(int i) {
        return daOclientsFILE.get(i);
    }



    public Either<ErrorCCustomer, Integer> saveClient(Customer i) {
        return daOclientsFILE.save(i);
    }

    public Either<ErrorCCustomer, Integer> updateClient(Customer i) {
        return daOclientsFILE.update(i);
    }

    public Either<ErrorCCustomer, Integer> delClient(int i) {
        Either<ErrorCCustomer, Customer> res = daOclientsFILE.get(i);
        if (res.isRight()) {
            Customer customer = res.get();
            return daOclientsFILE.delete(customer);
        } else {
            return Either.left(res.getLeft());
        }

    }

    /*Métodos con SQL*/

    public Either<ErrorCCustomer, List<Customer>> getAll() {
        return daOcustomerDB.getAll();
    }

    public Either<ErrorCCustomer, Customer> get(int id) {
        return daOcustomerDB.get(id);
    }

    public Either<ErrorCCustomer, Integer> delete(int i, boolean conf) {
        Either<ErrorCCustomer, Customer> res = daOcustomerDB.get(i);
        if (res.isRight()) {
            return daOcustomerDB.delete(i, conf);
        } else {
            return Either.left(res.getLeft());
        }
    }

    public Either<ErrorCCustomer, Integer> update(Customer customer){
        return daOcustomerDB.update(customer);
    }
}
