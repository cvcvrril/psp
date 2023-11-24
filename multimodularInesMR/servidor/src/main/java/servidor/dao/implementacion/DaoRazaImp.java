package servidor.dao.implementacion;

import domain.errores.ApiError;
import domain.modelo.Personaje;
import domain.modelo.Raza;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import servidor.common.SqlQueries;
import servidor.dao.ConstantsDao;
import servidor.dao.DaoRaza;
import servidor.dao.DbConnectionPool;
import servidor.domain.modelo.excepciones.BaseCaidaException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoRazaImp implements DaoRaza {

    private final DbConnectionPool db;

    @Inject
    public DaoRazaImp(DbConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<ApiError, List<Raza>> getAll() {
        List<Raza> razaList;
        Either<ApiError, List<Raza>> res;
        try (Connection myconnection = db.getConnection()) {
            Statement stmt = myconnection.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueries.SELECT_FROM_RAZAS);
            razaList = readRS(rs);
            res = Either.right(razaList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, Raza> get(int id) {
        return null;
    }

    @Override
    public Either<ApiError, Integer> add(Raza nuevaRaza) {
        return null;
    }

    @Override
    public Either<ApiError, Integer> update(Raza actualizadaRaza) {
        return null;
    }

    @Override
    public Either<ApiError, Integer> delete(int id) {
        return null;
    }

    private List<Raza> readRS(ResultSet rs) {
        try {
            List<Raza> personajeList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre_raza");
                String planetaOrigen = rs.getString("planeta_origen");
                personajeList.add(new Raza(id, nombre, planetaOrigen));
            }
            return personajeList;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
    }

}
