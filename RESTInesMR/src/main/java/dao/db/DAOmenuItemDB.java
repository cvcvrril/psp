package dao.db;

import common.Configuration;
import common.SQLqueries;
import dao.ConstantsDAO;
import dao.DAOmenuItem;
import dao.DBConnection;
import dao.modelo.MenuItem;
import domain.modelo.excepciones.BaseCaidaException;
import domain.modelo.excepciones.WrongObjectException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOmenuItemDB implements DAOmenuItem {

    private final DBConnection db;

    @Inject
    public DAOmenuItemDB(Configuration configuration, DBConnection db) {
        this.db = db;
    }

    public Either<ApiError, List<MenuItem>> getAll(){
        List<MenuItem> menuItemList = new ArrayList<>();
        Either<ApiError, List<MenuItem>> res;
        try (Connection myConnection = db.getConnection()){
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery(SQLqueries.SELECT_FROM_MENU_ITEMS);
            menuItemList = readRS(rs);
            res = Either.right(menuItemList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return  res;
    }

    public Either<ApiError, MenuItem> get(int id){
        Either<ApiError, MenuItem> res;
        try (Connection myConnection = db.getConnection()){
            PreparedStatement pstmt = myConnection.prepareStatement(SQLqueries.SELECT_FROM_MENU_ITEMS_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            List<MenuItem> menuItemList = readRS(rs);
            if (!menuItemList.isEmpty()){
                res = Either.right(menuItemList.get(0));
            } else {
                throw new WrongObjectException(ConstantsDAO.WRONG_OBJECT_EXCEPTION);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    private List<MenuItem> readRS(ResultSet rs){
        try {
            List<MenuItem> menuItemList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(ConstantsDAO.MENU_ITEM_ID);
                String nameM = rs.getString(ConstantsDAO.NAME);
                String desM = rs.getString(ConstantsDAO.DESCRIPTION);
                float priceM = rs.getFloat(ConstantsDAO.PRICE);
                menuItemList.add(new MenuItem(id, nameM, desM, priceM));
            }
            return menuItemList;
        }catch (SQLException e){
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
    }
}
