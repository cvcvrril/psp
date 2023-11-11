package domain.servicios;

import dao.db.DAOmenuItemDB;
import dao.modelo.MenuItem;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;

import java.util.List;

public class SERVmenuItems {

    private final DAOmenuItemDB DAOmenuItemDBd;

    @Inject
    public SERVmenuItems(DAOmenuItemDB DAOmenuItemDBd) {
        this.DAOmenuItemDBd = DAOmenuItemDBd;
    }

    public Either<ApiError, List<MenuItem>> getAll(){
        return DAOmenuItemDBd.getAll();
    }

    public Either<ApiError, MenuItem> get(int id){
        return DAOmenuItemDBd.get(id);
    }
}
