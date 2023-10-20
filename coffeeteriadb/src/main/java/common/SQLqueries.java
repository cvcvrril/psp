package common;

public class SQLqueries {

    public static final String SELECT_FROM_CUSTOMERS = "select * from customers ";
    public static final String DELETE_ORDERS = "DELETE FROM orders WHERE customer_id = ?";
    public static final String DELETE_CREDENTIALS = "DELETE FROM credentials WHERE customer_id = ?";
    public static final String DELETE_CUSTOMERS = "DELETE FROM customers WHERE id = ?";
    public static final String UPDATE_CUSTOMERS = "UPDATE customers SET first_name=?, last_name=?, email=?, phone_number=?, birth_date=? WHERE id=?";
    public static final String SELECT_CUSTOMERS_ID = "select * from customers where id = ?";

}
