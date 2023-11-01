package dao.db;

import common.Configuration;
import dao.ConstantsDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.MenuItem;
import model.errors.ErrorCMenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOMenuItemDB {

    //TODO: hacer el get(i) y el getAll()

    private final Configuration configuration;
    private final DBConnection db;

    @Inject
    public DAOMenuItemDB(Configuration configuration, DBConnection db) {
        this.configuration = configuration;
        this.db = db;
    }


    public Either<ErrorCMenuItem, List<MenuItem>> getAll(){
        List<MenuItem> menuItemList = new ArrayList<>();
        Either<ErrorCMenuItem, List<MenuItem>> res;
        try (Connection myConnection = db.getConnection()){
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery("select  * from menu_items");
            menuItemList = readRS(rs);
            res = Either.right(menuItemList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCMenuItem(e.getMessage(), 0));
        }
        return  res;
    }

    public Either<ErrorCMenuItem, MenuItem> get(int id){
        Either<ErrorCMenuItem, MenuItem> res;
        try (Connection myConnection = db.getConnection()){
            PreparedStatement pstmt = myConnection.prepareStatement("select  * from menu_items where menu_item_id=?");
            ResultSet rs = pstmt.executeQuery();
            List<MenuItem> menuItemList = readRS(rs);
            if (!menuItemList.isEmpty()){
                res = Either.right(menuItemList.get(0));
            } else {
                res = Either.left(new ErrorCMenuItem(ConstantsDAO.ERROR_READING_DATABASE, 0));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCMenuItem(e.getMessage(), 0));
        }
        return res;
    }

    private List<MenuItem> readRS(ResultSet rs) throws SQLException {
        List<MenuItem> menuItemList = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("menu_item_id");
            String nameM = rs.getString("name");
            String desM = rs.getString("description");
            float priceM = rs.getFloat("price");
            menuItemList.add(new MenuItem(id, nameM, desM, priceM));
        }
        return menuItemList;
    }

}
