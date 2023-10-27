package dao.interfaces;

import io.vavr.control.Either;
import model.Customer;

import model.errors.ErrorCCustomer;

import java.util.List;

public interface DAOclients {

    Either<ErrorCCustomer, List<Customer>> getAll();

    Either<ErrorCCustomer, Customer> get(int i);


    Either<ErrorCCustomer, Integer> save(Customer t);

    Either<ErrorCCustomer, Integer> update(Customer t);

    Either<ErrorCCustomer, Integer> delete(Customer t);


}
