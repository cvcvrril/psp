package dao.imp;

import dao.DAOorder;
import io.vavr.control.Either;
import model.Order;
import model.errors.ErrorC;

import java.util.List;

public class DAOorderIMP implements DAOorder {
    @Override
    public Either<ErrorC, List<Order>> Coffee_get(int id) {
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
