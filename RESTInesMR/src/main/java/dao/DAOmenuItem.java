package dao;

import dao.modelo.MenuItem;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;

import java.util.List;

public interface DAOmenuItem {

    Either<ApiError, List<MenuItem>> getAll();
    Either<ApiError, MenuItem> get(int id);

}
