package dao.db;

import common.Configuration;
import dao.DBConnection;
import dao.modelo.MenuItem;
import dao.modelo.errores.ErrorCMenuItem;
import domain.modelo.excepciones.BaseCaidaException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DAOMenuItemDB {

    //TODO: hacer el get(i) y el getAll()

    private final Configuration configuration;
    private final DBConnection db;

    @Inject
    public DAOMenuItemDB(Configuration configuration, DBConnection db) {
        this.configuration = configuration;
        this.db = db;
    }

    public Either<ApiError, List<MenuItem>> getAll(){
        List<MenuItem> menuItemList = new ArrayList<>();
        Either<ApiError, List<MenuItem>> res;
        try (Connection myConnection = db.getConnection()){
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery("select  * from menu_items");
            menuItemList = readRS(rs);
            res = Either.right(menuItemList);
        } catch (SQLException e) {
            throw new BaseCaidaException("Error al interactuar con la base de datos");
        }
        return  res;
    }

    public Either<ApiError, MenuItem> get(int id){
        Either<ApiError, MenuItem> res;
        try (Connection myConnection = db.getConnection()){
            PreparedStatement pstmt = myConnection.prepareStatement("select  * from menu_items where menu_item_id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            List<MenuItem> menuItemList = readRS(rs);
            if (!menuItemList.isEmpty()){
                res = Either.right(menuItemList.get(0));
            } else {
                throw new BaseCaidaException("Error al interactuar con la base de datos");
            }
        } catch (SQLException e) {
            throw new BaseCaidaException("Error al interactuar con la base de datos");
        }
        return res;
    }

    private List<MenuItem> readRS(ResultSet rs) throws SQLException {
        try {
            List<MenuItem> menuItemList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("menu_item_id");
                String nameM = rs.getString("name");
                String desM = rs.getString("description");
                float priceM = rs.getFloat("price");
                menuItemList.add(new MenuItem(id, nameM, desM, priceM));
            }
            return menuItemList;
        }catch (SQLException e){
            throw new BaseCaidaException("Error al interactuar con la base de datos");
        }
    }
}
