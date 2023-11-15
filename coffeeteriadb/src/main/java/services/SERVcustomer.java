package services;

import dao.db.DAOcustomerDB;
import dao.spring.DAOcustomerSpring;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Credential;
import model.Customer;
import model.errors.ErrorCCustomer;

import java.util.Collections;
import java.util.List;

public class SERVcustomer {

    private final DAOcustomerDB daOcustomerDB;
    private final DAOcustomerSpring daOcustomerSpring;

    @Inject
    public SERVcustomer(DAOcustomerDB daOcustomerDB, DAOcustomerSpring daOcustomerSpring) {
        this.daOcustomerDB = daOcustomerDB;
        this.daOcustomerSpring = daOcustomerSpring;
    }

    public Either<ErrorCCustomer, List<Customer>> getAll() {
         return daOcustomerSpring.getAll();
    }

    public Either<ErrorCCustomer, Customer> get(int id) {
        return daOcustomerSpring.get(id);
    }

    public Either<ErrorCCustomer, Integer> delete(int i, boolean conf) {
        Either<ErrorCCustomer, Customer> res = daOcustomerDB.get(i);
        if (res.isRight()) {
            return daOcustomerSpring.delete(i, conf);
        } else {
            return Either.left(res.getLeft());
        }
    }

    public Either<ErrorCCustomer, Integer> update(Customer customer) {
        return daOcustomerSpring.update(customer);
    }

    public Either<ErrorCCustomer, Integer> add(Customer customer, Credential credential) {
        return daOcustomerSpring.add(customer, credential);
    }
}
