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
    private final Configuration config;
    private final DBConnection db;

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
        Either<ErrorCCustomer,Customer> res;
        try (Connection myConnection = db.getConnection(); 
             PreparedStatement stmt = myConnection.prepareStatement("select * from customers where id = ?")) {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            List<Customer> customerList = readRS(rs);

            if (!customerList.isEmpty()){
               res = Either.right(customerList.get(0));
            }else {
                res = Either.left(new ErrorCCustomer("Error al leer datos", 0));
            }
            rs.close();
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
            LocalDate date = null;
            Date dateFromDB = rs.getDate("date_of_birth");
            if (dateFromDB != null) {
                date = dateFromDB.toLocalDate();
            }
            customerList.add(new Customer(id, first_name, last_name, email, phone, date));
        }
        return customerList;
    }

    /*update*/

    public Either<ErrorCCustomer, Integer> update(Customer customer){
        int rowsAffected;
        Either<ErrorCCustomer, Integer> res;
        try (Connection connection = db.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement("UPDATE customers SET first_name=?, last_name=?, email=?, phone_number=?, birth_date=? WHERE id=?");
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getSecondName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setInt(4, customer.getPhoneNumber());
            pstmt.setDate(5, Date.valueOf(customer.getDate()));
            pstmt.setInt(6, customer.getIdC());
            rowsAffected = pstmt.executeUpdate();
            res = Either.right(rowsAffected);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCCustomer(e.getMessage(), 0));
        }
        return res;
    }

    /*delete(id)*/

    public Either<ErrorCCustomer, Integer> delete(int id){
        int rowsAffected;
        Either<ErrorCCustomer, Integer> res;
        try (Connection connection = db.getConnection()) {
            PreparedStatement pstmtOrders = connection.prepareStatement("DELETE FROM orders WHERE customer_id = ?");
            pstmtOrders.setInt(1, id);
            pstmtOrders.executeUpdate();
            PreparedStatement pstmtCredentials = connection.prepareStatement("DELETE FROM credentials WHERE customer_id = ?");
            pstmtCredentials.setInt(1, id);
            pstmtCredentials.executeUpdate();
            PreparedStatement pstmtCustomer = connection.prepareStatement("DELETE FROM customers WHERE id = ?");
            pstmtCustomer.setInt(1, id);
            rowsAffected = pstmtCustomer.executeUpdate();
            res = Either.right(rowsAffected);
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
            res = Either.left(new ErrorCCustomer(e.getMessage(), 0));
        }
        return res;
    }

    /*add*/
}
