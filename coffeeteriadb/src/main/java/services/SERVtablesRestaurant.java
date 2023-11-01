package services;

import dao.db.DAOtablesDB;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.TableRestaurant;
import model.errors.ErrorCTables;

import java.util.List;

public class SERVtablesRestaurant {

    private final DAOtablesDB daOtablesDB;

    @Inject
    public SERVtablesRestaurant(DAOtablesDB daOtablesDB) {
        this.daOtablesDB = daOtablesDB;
    }

    public Either<ErrorCTables, List<TableRestaurant>> getAll(){
        return daOtablesDB.getAll();
    }

    public Either<ErrorCTables, TableRestaurant> get (int id){
        return daOtablesDB.get(id);
    }

}
