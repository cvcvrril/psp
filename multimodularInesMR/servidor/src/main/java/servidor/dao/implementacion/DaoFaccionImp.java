package servidor.dao.implementacion;

import domain.errores.ApiError;
import domain.modelo.Faccion;
import domain.modelo.Raza;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import servidor.common.SqlQueries;
import servidor.dao.ConstantsDao;
import servidor.dao.DaoFaccion;
import servidor.dao.DbConnectionPool;
import servidor.domain.modelo.excepciones.BaseCaidaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoFaccionImp implements DaoFaccion {

    private final DbConnectionPool db;

    @Inject
    public DaoFaccionImp(DbConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<ApiError, List<Faccion>> getAll() {
        List<Faccion> faccionList;
        Either<ApiError, List<Faccion>> res;
        try (Connection myconnection = db.getConnection()) {
            Statement stmt = myconnection.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueries.SELECT_FROM_FACCIONES);
            faccionList = readRS(rs);
            res = Either.right(faccionList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, List<Faccion>> get(int id) {
        List<Faccion> faccionList;
        Either<ApiError, List<Faccion>> res;
        try (Connection myconnection = db.getConnection()) {
            PreparedStatement pstmt = myconnection.prepareStatement(SqlQueries.SELECT_FACCIONES_JOIN);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            faccionList = readRS(rs);
            res = Either.right(faccionList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    private List<Faccion> readRS(ResultSet rs) {
        try {
            List<Faccion> faccionList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(ConstantsDao.IDFACCIONES);
                String nombreFaccion = rs.getString(ConstantsDao.NOMBRE_FACCION);
                faccionList.add(new Faccion(id,nombreFaccion));
            }
            return faccionList;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
    }

}
