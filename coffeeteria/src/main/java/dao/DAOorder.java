package dao;

import model.Order;
import io.vavr.control.Either;
import model.errors.ErrorCOrder;

import java.util.List;

public interface DAOorder {

    Either<ErrorCOrder, List<Order>> getAll();

    Either<ErrorCOrder, Order> getOrder(int id);

    Either<ErrorCOrder, Integer> save(Order t);

    Either<ErrorCOrder, Integer> update(Order t);

    Either<ErrorCOrder, Integer> delete(Order t);


}
