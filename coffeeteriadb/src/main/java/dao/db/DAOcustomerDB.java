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

//TODO: hacer los métodos get(id), delete, update, add

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

    public Either<ErrorCCustomer, List<Customer>> getAll() {
        List<Customer> customerList = new ArrayList<>();
//        Either<ErrorCCustomer, List<Customer>> res = null;
        try (Connection myConnection = db.getConnection()) {
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery(SQLqueries.SELECT_FROM_CUSTOMERS);
            customerList = readRS(rs);

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return Either.right(customerList);
    }

    public Either<ErrorCCustomer, Customer> get(int id) {
//        Either<ErrorCCustomer,Customer> res;
        Customer cus;
        try (Connection myConnection = db.getConnection()) {
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customer where id = ?");
            cus = readRS(rs).stream().filter(customer -> customer.getIdC() == id).findFirst().orElse(null);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return Either.right(cus);

    }

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
}
