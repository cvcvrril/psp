package services;

import dao.db.DAOcustomerDB;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.errors.ErrorCCustomer;

import java.util.List;

public class SERVclient {

    private final DAOcustomerDB daOcustomerDB;

    @Inject
    public SERVclient(DAOcustomerDB daOcustomerDB) {
        this.daOcustomerDB = daOcustomerDB;
    }

    public int getLastUsedId() {
        List<Customer> customers = daOcustomerDB.getAll().getOrNull();
        int lastUsedId = 0;
        for (Customer customer : customers) {
            if (customer.getIdC() > lastUsedId) {
                lastUsedId = customer.getIdC();
            }
        }
        return lastUsedId + 1;
    }

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

    public Either<ErrorCCustomer, Integer> update(Customer customer) {
        return daOcustomerDB.update(customer);
    }

    public Either<ErrorCCustomer, Integer> add(Customer customer) {
        return daOcustomerDB.add(customer);
    }


}
