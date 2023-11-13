package domain.servicios;

import dao.ConstantsDAO;
import dao.db.DAOmenuItemDB;
import dao.modelo.MenuItem;
import domain.modelo.excepciones.BadArgumentException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class SERVmenuItems {

    private final DAOmenuItemDB daOmenuItemDB;

    @Inject
    public SERVmenuItems(DAOmenuItemDB daOmenuItemDB) {
        this.daOmenuItemDB = daOmenuItemDB;
    }

    public Either<ApiError, List<MenuItem>> getAll(){
        return daOmenuItemDB.getAll();
    }

    public Either<ApiError, MenuItem> get(String idParam){
        try {
            Integer id = Integer.parseInt(idParam);
            return daOmenuItemDB.get(id);
        }catch (NumberFormatException e){
            log.error(e.getMessage(),e);
            throw new BadArgumentException(ConstantsDAO.BAD_ARGUMENT_EXCEPTION);
        }
    }
}
