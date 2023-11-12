package services;

import dao.db.DAOMenuItemDB;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.MenuItem;
import model.errors.ErrorCMenuItem;

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

    public Either<ErrorCMenuItem, List<MenuItem>> getListMenuItems(int id){
        return daoMenuItemDBd.getByMenuItemId(id);
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
