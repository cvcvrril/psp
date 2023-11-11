package dao;

import dao.modelo.TableRestaurant;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;

import java.util.List;

public interface DAOtables {

    Either<ApiError, List<TableRestaurant>> getAll();
    Either<ApiError, TableRestaurant> get(int i);
}
