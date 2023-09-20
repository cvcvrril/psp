package dao;

import model.Coffee;
import io.vavr.control.Either;

import java.util.List;

public interface DAOcoffee {

    Either<String, List<Coffee>>Coffee_get(int id);
    Either<String,List<Coffee>> getAll();
    int save(Coffee t);
    int uptdate (Coffee t);
    int delete (Coffee t);


}
