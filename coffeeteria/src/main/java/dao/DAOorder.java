package dao;

import model.Order;
import io.vavr.control.Either;
import model.errors.ErrorC;

import java.util.List;

public interface DAOorder {

    Either<ErrorC, List<Order>> getAll();

    Either<ErrorC, List<Order>> getOrder(int id);

    Either<ErrorC, Integer> save(Order t);

    Either<ErrorC, Integer> update(Order t);

    Either<ErrorC, Integer> delete(Order t);


}
