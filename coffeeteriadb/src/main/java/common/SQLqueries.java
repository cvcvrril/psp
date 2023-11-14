package common;

public class SQLqueries {

    public static final String SELECT_FROM_CUSTOMERS = "select * from customers";
    public static final String DELETE_ORDERS = "DELETE FROM orders WHERE customer_id = ?";
    public static final String DELETE_CREDENTIALS = "DELETE FROM credential WHERE id = ?";
    public static final String DELETE_CUSTOMERS = "DELETE FROM customers WHERE id = ?";
    public static final String UPDATE_CUSTOMERS = "UPDATE customers SET first_name=?, last_name=?, email=?, phone=?, date_of_birth=? WHERE id=?";
    public static final String INSERT_CUSTOMER = "INSERT INTO customers (first_name, last_name, email, phone, date_of_birth) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_CUSTOMERS_ID = "SELECT * FROM customers WHERE id = ?";
    public static final String SELECT_FROM_ORDERS = "SELECT * FROM orders";
    public static final String SELECT_ORDERS_ID = "SELECT * FROM orders WHERE order_id=?";
    public static final String DELETE_ORDER_ITEMS = "DELETE FROM order_items WHERE order_id=?";
    public static final String DELETE_ORDERS_ID = "DELETE FROM orders WHERE order_id=?";
    public static final String INSERT_ORDER = "INSERT INTO orders (order_date, customer_id, table_id) VALUES (?, ?, ?)";
    public static final String UPDATE_ORDERS = "update orders set order_date=?, customer_id=?, table_id=? where order_id=?";
    public static final String SELECT_FROM_ORDER_ITEMS = "select * from order_items";
    public static final String SELECT_FROM_RESTAURANT_TABLES = "select * from restaurant_tables";
    public static final String SELECT_NUMBER_ID = "select * from restaurant_tables where table_number_id = ?";
    public static final String INSERT_CREDENTIAL = "INSERT INTO credential (username, password, id)VALUES (?, ?, ?)";
    public static final String LINK_CREDENTIAL_ID = "UPDATE customers SET credential_id = ? WHERE id = ?";
    public static final String SELECT_FROM_CREDENTIAL = "select * from credential";
    public static final String INSERT_ORDER_ITEM = "INSERT INTO order_items (order_item_id, order_id, menu_item_id, quantity) VALUES (?, ?, ?, ?)";
    public static final String SELECT_CREDENTIAL_ID_FROM_CUSTOMERS_WHERE_ID = "SELECT credential_id FROM customers WHERE id=?";
    public static final String INSERT_ORDER_ITEM_GEN = "INSERT INTO order_items (order_id, menu_item_id, quantity) VALUES (?, ?, ?)";
}

