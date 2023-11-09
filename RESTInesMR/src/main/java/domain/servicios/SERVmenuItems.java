package domain.servicios;

import dao.db.DAOMenuItemDB;
import dao.modelo.MenuItem;
import dao.modelo.errores.ErrorCMenuItem;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class SERVmenuItems {

    private final DAOMenuItemDB daoMenuItemDBd;

    @Inject
    public SERVmenuItems(DAOMenuItemDB daoMenuItemDBd) {
        this.daoMenuItemDBd = daoMenuItemDBd;
    }

    public Either<ErrorCMenuItem, List<MenuItem>> getAll(){
        return daoMenuItemDBd.getAll();
    }

    public Either<ErrorCMenuItem, MenuItem> get(int id){
        return daoMenuItemDBd.get(id);
    }

    public Either<ErrorCMenuItem, String> getMenuItemName(int id){
        Either<ErrorCMenuItem, String> res;
        Either<ErrorCMenuItem, MenuItem> menuItemResult = daoMenuItemDBd.get(id);

        if(menuItemResult.isRight()) {
            res = Either.right(menuItemResult.get().getNameMItem());
        } else {
            res = Either.left(menuItemResult.getLeft());
        }
        return res;
    }


}
