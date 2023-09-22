package dao.imp;

import dao.DAOorder;
import io.vavr.control.Either;
import model.Order;
import model.errors.ErrorC;

import java.util.ArrayList;
import java.util.List;

public class DAOorderIMP implements DAOorder {

    private static List<Order> getOrders;
    static {
        getOrders = new ArrayList<>();
        getOrders.add(new Order(1, 1, 1));
        getOrders.add(new Order(2, 2, 1));
        getOrders.add(new Order(3, 2, 3));
    }

    @Override
    public Either<ErrorC, List<Order>> getOrder (int id) {
        return null;
    }

    @Override
    public Either<ErrorC, List<Order>> getAll() {
        return null;
    }

    @Override
    public int save(Order t) {
        return 0;
    }

    @Override
    public int uptdate(Order t) {
        return 0;
    }

    @Override
    public int delete(Order t) {
        return 0;
    }
}
