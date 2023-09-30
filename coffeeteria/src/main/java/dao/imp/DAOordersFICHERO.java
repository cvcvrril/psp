package dao.imp;

import dao.DAOorder;
import io.vavr.control.Either;
import model.Order;
import model.errors.ErrorC;

import java.util.List;

public class DAOordersFICHERO implements DAOorder {
    @Override
    public Either<ErrorC, List<Order>> getOrder(int id) {
        return null;
    }

    @Override
    public Either<ErrorC, Integer> save(Order t) {
        return null;
    }

    @Override
    public Either<ErrorC, Integer> update(Order t) {
        return null;
    }

    @Override
    public Either<ErrorC, Integer> delete(Order t) {
        return null;
    }

    @Override
    public Either<ErrorC, List<Order>> getAll() {
        return null;
    }

}
