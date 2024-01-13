package dao;

public class ConstantsDao {

    /*Orders*/

    public static final String ORDER_ID = "order_id";
    public static final String ORDER_DATE = "order_date";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String TABLE_ID = "table_id";

    /*Order Items*/

    public static final String ORDER_ITEM_ID = "order_item_id";
    public static final String MENU_ITEM_ID = "menu_item_id";
    public static final String QUANTITY = "quantity";

    /*Tables*/

    public static final String TABLE_NUMBER_ID = "table_number_id";
    public static final String NUMBER_OF_SEATS = "number_of_seats";

    /*Menu Items*/

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";


    public static final String BASE_CAIDA_EXCEPTION = "Error al interactuar con la base de datos";
    public static final String BAD_ARGUMENT_EXCEPTION = "Error al meter alguno de los argumentos";
    public static final String WRONG_OBJECT_EXCEPTION = "Error al seleccionar el objeto de la base de datos";

    public static final String COM_MYSQL_CJ_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String CACHE_PREP_STMTS = "cachePrepStmts";
    public static final String PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
    public static final String PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";
}

