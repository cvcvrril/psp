package dao;

import io.vavr.control.Either;
import model.Client;
import model.Order;
import model.errors.ErrorC;

import java.util.List;

public interface DAOclients {

    Either<ErrorC, List<Client>> getAll();

    Either<ErrorC, Client> get(int i);


    Either<ErrorC, Integer> save(Client t);

    Either<ErrorC, Integer> update(Client t);

    Either<ErrorC, Integer> delete(Client t);


}
