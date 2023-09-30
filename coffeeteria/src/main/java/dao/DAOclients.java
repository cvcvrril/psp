package dao;

import io.vavr.control.Either;
import model.Client;

import model.errors.ErrorCCustomer;

import java.util.List;

public interface DAOclients {

    Either<ErrorCCustomer, List<Client>> getAll();

    Either<ErrorCCustomer, Client> get(int i);


    Either<ErrorCCustomer, Integer> save(Client t);

    Either<ErrorCCustomer, Integer> update(Client t);

    Either<ErrorCCustomer, Integer> delete(Client t);


}
