package dao.staticTables;

import dao.DAOcoffee;
import io.vavr.control.Either;
import model.Coffee;

import java.util.List;

public class DAOcoffeeIMP implements DAOcoffee {
    @Override
    public Either<String, List<Coffee>> Coffee_get(int id) {
        return null;
    }

    @Override
    public Either<String, List<Coffee>> getAll() {
        return null;
    }

    @Override
    public int save(Coffee t) {
        return 0;
    }

    @Override
    public int uptdate(Coffee t) {
        return 0;
    }

    @Override
    public int delete(Coffee t) {
        return 0;
    }
}
