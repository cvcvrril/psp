package servidor.common;

public class SqlQueries {


    public static final String SELECT_FROM_PERSONAJES = "select * from personajes";
    public static final String SELECT_FROM_PERSONAJES_WHERE_ID = "select * from personajes where id=?";
    public static final String INSERT_INTO_PERSONAJES = "insert into personajes (nombre, raza, planeta_residencia) values (?, ?, ?)";
    public static final String UPDATE_PERSONAJES = "update personajes set nombre=?, planeta_residencia=? where id=? ";
    public static final String DELETE_FROM_FACCION_PERSONAJE_WHERE_ID_PERSONAJE = "DELETE FROM faccion_personaje WHERE id_personaje=?";
    public static final String DELETE_FROM_PERSONAJES_WHERE_ID = "DELETE FROM personajes WHERE id=?";
    public static final String DELETE_FROM_FACCION_PERSONAJE_WHERE_ID_FACCION = "DELETE FROM faccion_personaje WHERE id_faccion=?";
    public static final String DELETE_FROM_PERSONAJES_WHERE_ID_NOT_IN_SELECT_ID_PERSONAJE_FROM_FACCION_PERSONAJE = "DELETE FROM personajes WHERE id NOT IN (SELECT id_personaje FROM faccion_personaje)";
    public static final String SELECT_FACCIONES_JOIN = "SELECT facciones.* FROM facciones INNER JOIN faccion_personaje ON facciones.idfacciones = faccion_personaje.id_faccion WHERE faccion_personaje.id_personaje = ?";
    public static final String SELECT_FROM_FACCIONES = "select * from facciones";
    public static final String SELECT_FROM_RAZAS = "select * from razas";
    public static final String SELECT_FROM_CREDENCIALES_WHERE_USUARIO = "select * from credenciales where usuario=?";
    public static final String SELECT_FROM_CREDENCIALES_WHERE_ID = "select * from credenciales where id =?";
    public static final String INSERT_INTO_CREDENCIALES_USUARIO_PASS_VALUES = "insert into credenciales (usuario, pass) values (?, ?)";
}
