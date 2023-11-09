package domain.servicios;

import dao.db.DAOorderDB;
import dao.modelo.Order;
import dao.modelo.errores.ErrorCOrder;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class SERVorder {
    private final DAOorderDB daOorderDB;


    @Inject
    public SERVorder(DAOorderDB daOorderDB) {
        this.daOorderDB = daOorderDB;
    }

    public List<Order> getAll() {
        return daOorderDB.getAll().getOrNull();
    }

    public Either<Exception, Order> getOrder(int i) {
        if (i > 0)
            return daOorderDB.get(i);
        else
            throw new RuntimeException();

    }

    public Either<ErrorCOrder, Integer> add(Order order) {
        return daOorderDB.add(order);
    }

    public Either<ErrorCOrder, Integer> updateOrder(Order o) {
        return daOorderDB.update(o);
    }

    public Either<ErrorCOrder, Integer> delOrder(int i) {
        return daOorderDB.delete(i);
    }

}
