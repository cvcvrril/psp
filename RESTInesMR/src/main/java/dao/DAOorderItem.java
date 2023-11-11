package dao;

import dao.modelo.OrderItem;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;

import java.util.List;

public interface DAOorderItem {

    Either<ApiError, List<OrderItem>> getAll();
    Either<ApiError, OrderItem> get(int id);
    Either<ApiError, List<OrderItem>> getByOrderId(int orderId);
    Either<ApiError, Integer> update(OrderItem orderItem);

}
