package domain.servicios;

import dao.db.DAOtablesDB;
import dao.modelo.TableRestaurant;
import dao.modelo.errores.ErrorCTables;
import io.vavr.control.Either;
import jakarta.inject.Inject;

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
