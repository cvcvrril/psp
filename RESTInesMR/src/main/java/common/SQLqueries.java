package common;

public class SQLqueries {
    public static final String SELECT_FROM_ORDERS = "SELECT * FROM orders";
    public static final String SELECT_ORDERS_ID = "SELECT * FROM orders WHERE order_id=?";
    public static final String DELETE_ORDER_ITEMS = "DELETE FROM order_items WHERE order_id=?";
    public static final String DELETE_ORDERS_ID = "DELETE FROM orders WHERE order_id=?";
    public static final String INSERT_ORDER = "INSERT INTO orders (order_date, customer_id, table_id) VALUES (?, ?, ?)";
    public static final String UPDATE_ORDERS = "update orders set order_date=?, customer_id=?, table_id=? where order_id=?";
    public static final String SELECT_FROM_ORDER_ITEMS = "select * from order_items";
    public static final String SELECT_FROM_RESTAURANT_TABLES = "select * from restaurant_tables";
    public static final String SELECT_NUMBER_ID = "select * from restaurant_tables where table_number_id = ?";
    public static final String INSERT_ORDER_ITEM = "INSERT INTO order_items (order_item_id, order_id, menu_item_id, quantity) VALUES (?, ?, ?, ?)";
    public static final String SELECT_FROM_MENU_ITEMS = "select  * from menu_items";
    public static final String SELECT_FROM_MENU_ITEMS_ID = "select  * from menu_items where menu_item_id=?";
    public static final String SELECT_FROM_ORDER_ITEMS_ID = "select * from order_items where order_item_id = ?";
    public static final String SELECT_FROM_ORDER_ITEMS_WHERE_ORDER_ID = "select * from order_items where order_id = ?";
    public static final String UPDATE_ORDER_ITEMS = "update order_items set order_item_id=?, order_id=?, menu_item_id=?, quantity=?";
}

