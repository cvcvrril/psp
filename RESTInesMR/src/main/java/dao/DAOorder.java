package dao;

import dao.modelo.Order;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;

import java.util.List;

public interface DAOorder {

    Either<ApiError, List<Order>> getAll();
    Either<ApiError, Order> get(int id);
    Either<ApiError, Integer> update(Order order);
    Either<ApiError, Integer> delete(int id);
    Either<ApiError, Integer> add(Order order);

}
