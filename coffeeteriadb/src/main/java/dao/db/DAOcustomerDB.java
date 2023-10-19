package dao.db;

import common.Configuration;
import common.SQLqueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.errors.ErrorCCustomer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TODO: hacer los métodos get(id), update, add

@Log4j2
public class DAOcustomerDB {

    /*Atb*/
    private Configuration config;
    private DBConnection db;

    /*Cons*/
    @Inject

    public DAOcustomerDB(Configuration config, DBConnection db) {
        this.config = config;
        this.db = db;
    }

    /*Methods*/


    /*getAll()*/
    public Either<ErrorCCustomer, List<Customer>> getAll() {
        List<Customer> customerList = new ArrayList<>();
        Either<ErrorCCustomer, List<Customer>> res;
        try (Connection myConnection = db.getConnection()) {
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery(SQLqueries.SELECT_FROM_CUSTOMERS);
            customerList = readRS(rs);
            res = Either.right(customerList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCCustomer(e.getMessage(), 0));
        }
        return res;
    }


    /*get(id)*/
    public Either<ErrorCCustomer, Customer> get(int id) {
        Customer cus;
        Either<ErrorCCustomer,Customer> res;
        try (Connection myConnection = db.getConnection(); 
             PreparedStatement stmt = myConnection.prepareStatement("select * from customer where id = ?")) {
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            cus = readRS(rs).stream().filter(customer -> customer.getIdC() == id).findFirst().orElse(null);
            res = Either.right(cus);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCCustomer(e.getMessage(),0));
        }
        return res;
    }

    /*readRS(rs)*/
    private List<Customer> readRS(ResultSet rs) throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            String email = rs.getString("email");
            int phone = rs.getInt("phone");
            LocalDate date = rs.getDate("date_of_birth").toLocalDate();
            customerList.add(new Customer(id, first_name, last_name, email, phone, date));
        }
        return customerList;
    }

    /*update*/

    public Either<ErrorCCustomer, Integer> update(int id){
        Either<ErrorCCustomer, Integer> res;
        try (Connection connection = db.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement("");
        res = Either.right();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCCustomer(e.getMessage(), 0));
        }
        return res;
    }

    /*delete(id)*/

    public Either<ErrorCCustomer, Integer> delete(int id){
        int rowsAffected = 0;
        Either<ErrorCCustomer, Integer> res;
        try (Connection connection = db.getConnection()) {
            PreparedStatement pstmt  = connection.prepareStatement("delete * from customer where id = ?");
            rowsAffected = pstmt.executeUpdate();
            res = Either.right(rowsAffected);
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
            res = Either.left(new ErrorCCustomer(e.getMessage(), 0));
        }
        return res;
    }

    /*add*/
}
