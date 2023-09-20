package dao;

import model.Order;
import io.vavr.control.Either;

import java.util.List;

public interface DAOorder {

    Either<String, List<Order>>Coffee_get(int id);
    Either<String,List<Order>> getAll();
    int save(Order t);
    int uptdate (Order t);
    int delete (Order t);


}
