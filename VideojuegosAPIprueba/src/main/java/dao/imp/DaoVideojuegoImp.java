package dao.imp;

import dao.ConstantsDAO;
import dao.DaoVideojuego;
import dao.connection.DBConnectionPool;
import dao.modelo.Videojuego;
import domain.excepciones.BaseCaidaException;
import jakarta.excepciones.ApiError;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoVideojuegoImp implements DaoVideojuego {

    private final DBConnectionPool pool;

    @Inject
    public DaoVideojuegoImp(DBConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Either<ApiError, List<Videojuego>> getAll() {
        Either<ApiError, List<Videojuego>> res;
        List<Videojuego> videojuegoList;
        try (Connection connection = pool.getConnection()){
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select * from videojuego");
            videojuegoList = readRS(rs);
            res = Either.right(videojuegoList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    private List<Videojuego> readRS(ResultSet rs) throws SQLException {
        List<Videojuego> videojuegoList = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String titulo = rs.getString("titulo");
            LocalDateTime dateTime = null;
            Timestamp timestamp = rs.getTimestamp("fecha");
            if (timestamp != null) {
                dateTime = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
            String descripcion = rs.getString("descripcion");
            videojuegoList.add(new Videojuego(id,titulo, dateTime, descripcion));
        }
        return videojuegoList;
    }

}
