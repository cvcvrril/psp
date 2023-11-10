package domain.servicios;

import dao.db.DAOMenuItemDB;
import dao.modelo.MenuItem;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;

import java.util.List;

public class SERVmenuItems {

    private final DAOMenuItemDB daoMenuItemDBd;

    @Inject
    public SERVmenuItems(DAOMenuItemDB daoMenuItemDBd) {
        this.daoMenuItemDBd = daoMenuItemDBd;
    }

    public Either<ApiError, List<MenuItem>> getAll(){
        return daoMenuItemDBd.getAll();
    }

    public Either<ApiError, MenuItem> get(int id){
        return daoMenuItemDBd.get(id);
    }
}
