package domain.servicios;

import dao.db.DAOorderDB;
import dao.modelo.Order;
import dao.modelo.errores.ErrorCOrder;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;

import java.util.List;

public class SERVorder {
    private final DAOorderDB daOorderDB;


    @Inject
    public SERVorder(DAOorderDB daOorderDB) {
        this.daOorderDB = daOorderDB;
    }

    public Either <ApiError,List<Order>> getAll() {
        return daOorderDB.getAll();
    }

    public Either<ApiError, Order> getOrder(int i) {
        return daOorderDB.get(i);

    }

    public Either<ApiError, Integer> add(Order order) {
        return daOorderDB.add(order);
    }

    public Either<ApiError, Integer> updateOrder(Order o) {
        return daOorderDB.update(o);
    }

    public Either<ApiError, Integer> delOrder(int i) {
        return daOorderDB.delete(i);
    }

}
