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
        Either<ErrorCCustomer, List<Customer>> res = null;
        try (Connection myConnection = db.getConnection()) {
            Statement stmt = myConnection.createStatement();
            {
                ResultSet rs = stmt.executeQuery(SQLqueries.SELECT_FROM_CUSTOMERS);
                List<Customer> customerList = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");
                    String email = rs.getString("email");
                    int phone = rs.getInt("phone");
                    LocalDate date = rs.getDate("date_of_birth").toLocalDate();
                    customerList.add(new Customer(id, first_name, last_name, email, phone, date));

                    res = Either.right(customerList);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return res;
    }
}
