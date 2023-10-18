package dao;

import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDate;

@Log4j2
public class test {

    public static void main(String[] args) {
        try (Connection myConnection = DriverManager.getConnection("jdbc:mysql://dam2.mysql.iesquevedo.es:3335/inesmartinez_restaurant", "root", "quevedo2dam");
             Statement stmt = myConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from customers ");
            while (rs.next()) {
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                LocalDate date = rs.getDate("date_of_birth").toLocalDate();

                System.out.println(id + ", " + first_name + ", " + last_name + ", " + email + ", " + phone + ", " + date);
            }


        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
}
