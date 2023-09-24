package dao;

import model.Order;
import io.vavr.control.Either;
import model.errors.ErrorC;

import java.util.List;

public interface DAOorder {

    Either<ErrorC, List<Order>> getOrder(int id);

    Either<ErrorC, List<Order>> getAll();

    int save(Order t);

    int uptdate(Order t);

    int delete(Order t);


}
