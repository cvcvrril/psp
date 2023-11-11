package domain.servicios;

import dao.db.DAOmenuItemDB;
import dao.modelo.MenuItem;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;

import java.util.List;

public class SERVmenuItems {

    private final DAOmenuItemDB daOmenuItemDB;

    @Inject
    public SERVmenuItems(DAOmenuItemDB daOmenuItemDB) {
        this.daOmenuItemDB = daOmenuItemDB;
    }

    public Either<ApiError, List<MenuItem>> getAll(){
        return daOmenuItemDB.getAll();
    }

    public Either<ApiError, MenuItem> get(int id){
        return daOmenuItemDB.get(id);
    }
}
